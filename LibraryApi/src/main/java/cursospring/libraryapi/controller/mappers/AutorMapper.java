package cursospring.libraryapi.controller.mappers;

import cursospring.libraryapi.controller.dto.AutorDTO;
import cursospring.libraryapi.controller.dto.DetalhesAutor;
import cursospring.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorDTO deAutorPraDTO(Autor autor);

    Autor deDTOPraAutor (AutorDTO dto);

    DetalhesAutor detalhesDoAutor (Autor autor);
}
