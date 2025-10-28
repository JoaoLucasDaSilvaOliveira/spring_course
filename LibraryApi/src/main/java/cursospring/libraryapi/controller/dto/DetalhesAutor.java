package cursospring.libraryapi.controller.dto;

import java.time.LocalDate;

public record DetalhesAutor (
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) { }
