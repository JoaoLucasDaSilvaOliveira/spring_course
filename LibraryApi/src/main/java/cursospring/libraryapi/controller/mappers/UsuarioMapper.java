package cursospring.libraryapi.controller.mappers;

import cursospring.libraryapi.controller.dto.UsuarioDTO;
import cursospring.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity (UsuarioDTO dto);

    UsuarioDTO toDTO (Usuario usuario);
}
