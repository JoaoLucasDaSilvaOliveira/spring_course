package cursospring.libraryapi.controller.dto;

import cursospring.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarLivroDTO (
        @ISBN
        String isbn,
        @NotBlank
        String titulo,
        @NotNull
        @PastOrPresent
        LocalDate dataPublicacao,
        @NotNull
        GeneroLivro genero,
        @NotNull
        BigDecimal preco,
        @NotNull
        @UUID(message = "Forneça um UUID válido")
        String idAutor
) { }
