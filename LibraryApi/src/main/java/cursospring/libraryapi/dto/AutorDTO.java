package cursospring.libraryapi.dto;

import cursospring.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {
    public Autor mapearParaAutor(){
        return new Autor(
          null,
          nome,
          dataNascimento,
          nacionalidade,
          new ArrayList<>(),
        null,
        null,
        null
        );
    }
}
