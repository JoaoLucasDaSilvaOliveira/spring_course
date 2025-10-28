package cursospring.libraryapi.controller.dto;

import cursospring.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DetalhesLivroDTO (
        UUID id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        DetalhesAutor autor
    ) { }
