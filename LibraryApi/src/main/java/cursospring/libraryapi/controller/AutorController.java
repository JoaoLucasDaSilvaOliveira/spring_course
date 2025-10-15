package cursospring.libraryapi.controller;

import cursospring.libraryapi.dto.AutorDTO;
import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.service.AutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
@AllArgsConstructor
public class AutorController {

    private AutorService service;

    @PostMapping
    public ResponseEntity<Void> cadastrarAutor(@RequestBody AutorDTO autor){
        Autor entidadeMapeada = autor.mapearParaAutor();
        service.salvar(entidadeMapeada);

        //retornar uri do autor criado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entidadeMapeada.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable(name = "id") String id){
        try {
            UUID idAutor = UUID.fromString(id);
            Autor autor = service.obterPorId(idAutor);
            AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<AutorDTO>(HttpStatus.NOT_FOUND);
        }
    }

}
