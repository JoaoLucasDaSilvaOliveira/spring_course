package cursospring.libraryapi.controller;

import cursospring.libraryapi.controller.dto.AutorDTO;
import cursospring.libraryapi.controller.dto.ErroReposta;
import cursospring.libraryapi.exceptions.AutorComLivrosException;
import cursospring.libraryapi.exceptions.DuplicadoException;
import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/autores")
@AllArgsConstructor
public class AutorController {

    private AutorService service;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<?> cadastrarAutor(@RequestBody @Valid  AutorDTO autor, Authentication auth){
        try{
            UUID idSalvo = service.salvar(autor, auth);
            //retornar uri do autor criado
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(idSalvo)
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (DuplicadoException e) {
            return new ResponseEntity<ErroReposta>(ErroReposta.duplicado("Registro Duplicado"), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable(name = "id") String id){
        try {
            UUID idAutor = UUID.fromString(id);
            Autor autor = service.obterPorId(idAutor).get();
            AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<AutorDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<?> excluirAutor (@PathVariable String id){
        try{
            service.excluirPorId(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        } catch (AutorComLivrosException e) {
            return new ResponseEntity<ErroReposta>(new ErroReposta(400, e.getMessage(), List.of()), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ErroReposta>(new ErroReposta(404, e.getMessage(), List.of()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(Map.of(
            "status", 400,
            "message", "Erro na exclusão.",
            "errors", new String[] {e.getMessage(), e.toString(),}
                 ),
            HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR, GERENTE')")
    public ResponseEntity<List<AutorDTO>> obterAutorComFiltro(
            //como eh um filtro, tudo pode ser não requerido
        @RequestParam (value = "nome", required = false) String nome,
        @RequestParam (value = "nacionalidade", required = false) String nacionalidade
    ) {
        List<AutorDTO> autorDTOS = new ArrayList<>();

        //se não passar nada entende-se que é uma busca por todos
        if (nome == null && nacionalidade == null){
            List<Autor> todosOsAutores = service.obterTodos();
            todosOsAutores.forEach(
    autor -> autorDTOS.add(
                    new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade())
                 )
            );
        } else {
            List<Autor> autores = service.obterAutorFiltro(nome, nacionalidade);
            autores.forEach(
                    autor -> autorDTOS.add(
                            new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade())
                    )
            );
        }
        return new ResponseEntity<>(autorDTOS, autorDTOS.isEmpty() ? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<?> atualizarAutor (
            @PathVariable String id,
            @RequestBody @Valid AutorDTO autorDTO
    ){
        try{
            UUID idAutor = UUID.fromString(id);
            if (!service.existePorId(idAutor)){
                return new ResponseEntity<ErroReposta>(new ErroReposta(404, "Não há autor com este Id", List.of()), HttpStatus.NOT_FOUND);
            }
            service.atualizar(autorDTO, idAutor);
            return ResponseEntity.noContent().build();
        } catch (DuplicadoException e){
            return new ResponseEntity<ErroReposta>(ErroReposta.duplicado("Registro Duplicado"), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException e){
            return ResponseEntity.unprocessableEntity()./*body(Map.of(
                    "status", 422,
                    "message", "Erro de validação",
                    "errors"
            ))
            vai ser implementado depois*/
            build();
        }
    }
}
