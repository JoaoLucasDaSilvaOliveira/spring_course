package cursospring.libraryapi.service;

import cursospring.libraryapi.controller.dto.AtualizarLivroDTO;
import cursospring.libraryapi.controller.dto.DetalhesLivroDTO;
import cursospring.libraryapi.controller.dto.LivroDTO;
import cursospring.libraryapi.controller.mappers.LivroMapper;
import cursospring.libraryapi.exceptions.DuplicadoException;
import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import cursospring.libraryapi.repository.LivroRepository;
import cursospring.libraryapi.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final AutorService autorService;
    private final LivroMapper mapper;
    private final SecurityService securityService;

    public Livro mapearParaLivro(LivroDTO livroDTO) throws DuplicadoException {
        if (livroDTO.dataPublicacao().isAfter(LocalDate.of(2020,1,1)) && livroDTO.preco() == null){
            throw new IllegalArgumentException("Livros publicados após 2020 precisam ter preço");
        }
        Optional<Autor> autor = autorService.obterPorId(UUID.fromString(livroDTO.idAutor()));
        if (autor.isEmpty()){
            throw new NoSuchElementException("Não existe este autor cadastrado na base de dados");
        }
        if (isDuplicated(livroDTO)){
            throw new DuplicadoException("Já existe um livro cadastrado com essas características");
        }
        Livro livro =  mapper.deDTOPraLivro(livroDTO);
        livro.setAutor(autor.get());
        return livro;
    }

    public void saveLivro (Livro livro, Authentication auth){
        livro.setUsuario(securityService.obterUsuarioPorAuth(auth));
        repository.save(livro);
    }

    public DetalhesLivroDTO obterLivro (UUID id){
        try{
            Livro livro = findLivroById(id);
            return mapper.detalhesDoLivro(livro);
        } catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public void excluirLivro (UUID id){
        try{
            Livro livro = findLivroById(id);
            repository.delete(livro);
        } catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }

    public List<DetalhesLivroDTO> obterLivrosFiltro (String isbn, String titulo, String nomeAutor, GeneroLivro genero, Year anoPublicacao){
        //  Como podemos ter mais de um livro com as mesmas caracteríticas nas buscas, usamos um set
        Set<DetalhesLivroDTO> livros = new HashSet<>(); // aqui guardaremos o que vem do bd
        if (isbn == null && (titulo == null || titulo.isBlank()) && (nomeAutor == null || nomeAutor.isBlank()) && genero == null && anoPublicacao == null){
            throw new NoSuchElementException();
        }
        //DIVIDIREMOS EM DUAS BUSCAS
        // - POR NOME_AUTOR E ANO DE PUBLICAÇÃO
        repository.obterLivroPorNomeAutor(nomeAutor != null ? nomeAutor : "", anoPublicacao != null ? anoPublicacao.getValue() : 0).forEach(livro -> {
            livros.add(mapper.detalhesDoLivro(livro));
        });
        // - POR QUERY ESPECÍFICA
        repository.findLivroByIsbnIgnoreCaseOrTituloIgnoreCaseOrGenero(isbn, titulo, genero).forEach(livro -> {
            livros.add(mapper.detalhesDoLivro(livro));
        });
        return List.copyOf(livros);
    }

    public List<DetalhesLivroDTO> obterTodosOsLivrosFiltro (){
        Set<DetalhesLivroDTO> livros = new HashSet<>();
        repository.findAll().forEach(livro -> livros.add(mapper.detalhesDoLivro(livro)));
        return List.copyOf(livros);
    }

    public void atualizarLivro (AtualizarLivroDTO dto, UUID id) throws DuplicadoException{
        Optional<Livro> oldLivro = repository.findById(id);
        if (oldLivro.isEmpty()){
            throw new NoSuchElementException();
        }
        if (oldLivro.get().getIsbn().equals(dto.isbn())){
            //nesse caso estamos atualizando outras coisas e não o isbn
            Livro newLivro = mapper.deAtualizarParaLivro(dto);
            newLivro.setId(oldLivro.get().getId());
            newLivro.setAutor(oldLivro.get().getAutor());
            newLivro.setDataCadastro(oldLivro.get().getDataCadastro());
            newLivro.setUsuario(oldLivro.get().getUsuario());
            repository.save(newLivro);
        } else if (hasIsbn(dto.isbn())) {
            throw new DuplicadoException("ISBN já cadastrado");
        } else {
            Livro newLivro = mapper.deAtualizarParaLivro(dto);
            newLivro.setId(oldLivro.get().getId());
            newLivro.setAutor(oldLivro.get().getAutor());
            newLivro.setDataCadastro(oldLivro.get().getDataCadastro());
            newLivro.setUsuario(oldLivro.get().getUsuario());
            repository.save(newLivro);
        }
    }

    //validações internas
    private boolean isDuplicated(LivroDTO dto){
        return repository.existsLivroByIsbnIgnoreCaseOrTituloIgnoreCase(dto.isbn(), dto.titulo());
    }
    private boolean hasIsbn (String isbn){
        return repository.existsLivroByIsbnIgnoreCase(isbn);
    }
    private Livro findLivroById (UUID id){
        Optional<Livro> livro = repository.findById(id);
        if (livro.isEmpty()){
            throw new NoSuchElementException();
        }
        return livro.get();
    }

}
