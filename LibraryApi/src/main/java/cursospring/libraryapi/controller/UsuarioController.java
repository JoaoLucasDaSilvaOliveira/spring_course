package cursospring.libraryapi.controller;

import cursospring.libraryapi.controller.dto.UsuarioDTO;
import cursospring.libraryapi.controller.mappers.UsuarioMapper;
import cursospring.libraryapi.model.Usuario;
import cursospring.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
