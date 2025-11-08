package cursospring.libraryapi.service;

import cursospring.libraryapi.controller.dto.AutorDTO;
import cursospring.libraryapi.controller.mappers.AutorMapper;
import cursospring.libraryapi.exceptions.AutorComLivrosException;
import cursospring.libraryapi.exceptions.DuplicadoException;
import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.repository.AutorRepository;
import cursospring.libraryapi.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AutorService {

    private AutorRepository repository;
    private AutorMapper mapper;
    private SecurityService securityService;

    public UUID salvar(AutorDTO dto, Authentication auth) throws DuplicadoException {
        //validação
        if (!verificaAutorDuplicado(dto)){
            throw new DuplicadoException("Já existe um autor com essas características");
        }
        Autor entidadeMapeada = mapper.deDTOPraAutor(dto);
        entidadeMapeada.setUsuario(securityService.obterUsuarioPorAuth(auth));
        repository.save(entidadeMapeada);
        return entidadeMapeada.getId();
    }

    public Optional<Autor> obterPorId (UUID id){
        return repository.findById(id);
    }

    public void excluirPorId(UUID id) throws AutorComLivrosException {
        //validação
        if (!repository.existsById(id)){
            throw new NoSuchElementException("Não existe autor com este id");
        }
        if (temLivro(id)){
            repository.deleteById(id);
        } else {
            throw new AutorComLivrosException("Não é possível excluir autor, ele possui livros");
        }

    }

    public List<Autor> obterAutorFiltro (String nome, String nacionalidade){
        return repository.findAutorByNomeIgnoreCaseOrNacionalidadeIgnoreCase(nome, nacionalidade);
    }

    public List<Autor> obterTodos(){
        return repository.findAll();
    }

    public void atualizar (AutorDTO autorDTO, UUID idAutorOriginal) throws DuplicadoException {
        if (!verificaAutorDuplicado(autorDTO)){
            throw new DuplicadoException("Já existe um autor com essas características");
        }
        Autor autor = mapper.deDTOPraAutor(autorDTO);
        autor.setId(idAutorOriginal);
        repository.save(autor);
    }

    /**
     * @param autorDTO contem os dados vindos do cliente
     * @return false se tiver não tiver nenhum autor com as características do DTO ou true se já houver aquele autor
     */
    private boolean verificaAutorDuplicado(AutorDTO autorDTO){
        return repository.findAutorByNomeIgnoreCaseAndNacionalidadeIgnoreCaseAndDataNascimento(autorDTO.nome(), autorDTO.nacionalidade(), autorDTO.dataNascimento()).isEmpty();
    }

    /**
     *
     * @param idAutor Id do autor
     * @return false se não tiver livros e true se tiver
     */
    private boolean temLivro(UUID idAutor){
        if (repository.findById(idAutor).isPresent()){
            Autor autor = repository.findById(idAutor).get();
            return autor.getLivros().isEmpty();
        }
        return false;
    }

    public boolean existePorId(UUID id){
        return repository.existsById(id);
    }

    /*
        NÃO USAREMOS MAS É POSSÍVEL FAZER PESQUISAS COM EXAMPLE<S>
        Como?
        Autor autor = new Autor(); precisa fornecer criar um objeto
        autor.setNome("NomeExemplo");
        autor.setNacionalidade("NacionalidadeExemplo"); seta o que você vai usar como exemplo, nesse caso quero nome e nacionalidade
        ExampleMatcher exampleMatcher = ExampleMatcher -> pode ou não passar um matcher, configurando como funcionará nossa procura
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); = ILIKE %string%
        Example<Autor> example = Example.of(autor, exampleMatcher); cria um exemplo usando o objeto e a configuração de busca
        repository.findAll(example); realiza a procura e retorna uma lista do objeto procurado
     */
}

