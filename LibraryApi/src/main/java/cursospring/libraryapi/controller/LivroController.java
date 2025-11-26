package cursospring.libraryapi.controller;

import cursospring.libraryapi.controller.dto.*;
import cursospring.libraryapi.exceptions.DuplicadoException;
import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import cursospring.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Validated
public class LivroController {

    private final LivroService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<?> criarLivro(@RequestBody @Valid LivroDTO dto, Authentication auth){
        try{
            Livro livro = service.mapearParaLivro(dto);
            service.saveLivro(livro, auth);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(livro.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (DuplicadoException e){
            return new ResponseEntity<ErroReposta>(ErroReposta.duplicado("Registro Duplicado"), HttpStatus.CONFLICT);
        }
        catch (IllegalArgumentException | NoSuchElementException e){
            return new ResponseEntity<ErroReposta>(new ErroReposta(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), List.of()
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<?> procurarLivro(@PathVariable String id){
        try{
            UUID idAutor = UUID.fromString(id);
            DetalhesLivroDTO detalhesLivroDTO = service.obterLivro(idAutor);
            System.out.println(detalhesLivroDTO);
            return new ResponseEntity<DetalhesLivroDTO>(detalhesLivroDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<ErroReposta>(ErroReposta.idIncorreto(List.of(
                                new ErroCampoDTO("Id", "O Id precisa ser válido")
                        )), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<?> deletarLivro (@PathVariable String id){
        try{
            UUID idAutor = UUID.fromString(id);
            service.excluirLivro(idAutor);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return new ResponseEntity<ErroReposta>(ErroReposta.idIncorreto(List.of(
                    new ErroCampoDTO("Id", "O Id precisa ser válido")
            )), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'ROLE_GERENTE')")
    public ResponseEntity<?> obterLivroFiltro (
            @ISBN (message = "Forneça um ISBN válido")
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String nomeAutor,
            @RequestParam(required = false) GeneroLivro genero,
            @PastOrPresent(message = "Não pode informar data futura")
            @RequestParam(required = false) Year anoPublicacao
            ) {
        try{
            List<DetalhesLivroDTO> livros = service.obterLivrosFiltro(isbn, titulo, nomeAutor, genero, anoPublicacao);
            return livros.isEmpty() ?
             new ResponseEntity<>(Map.of("mensagem", "Não encontrado nenhum livro com essas características"), HttpStatus.NOT_FOUND) : new ResponseEntity<>(livros, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(Map.of(
                    "mensagem", "Retornando todos os livros cadastrados",
                    "livro cadastrados", service.obterTodosOsLivrosFiltro()
            ), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<?> atualizarLivro (@RequestBody AtualizarLivroDTO dto, @PathVariable String id) {
        try{
            UUID idLivro = UUID.fromString(id);
            service.atualizarLivro(dto, idLivro);
            return ResponseEntity.noContent().build();
        } catch (DuplicadoException e) {
            throw new RuntimeException(e);
        }
    }
}
