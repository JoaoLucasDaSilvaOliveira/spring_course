package cursospring.libraryapi.controller.dto;

import cursospring.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroDTO(
        @ISBN
        @NotBlank(message = "O número de ISBN não pode ser vazio")
        String isbn,
        @NotBlank(message = "O título não pode ser vazio")
        String titulo,
        @PastOrPresent(message = "A data de publicação deve ser igual ou anterior à data atual")
        LocalDate dataPublicacao,
        @NotNull(message = "É obrigatório informar um gênero válido")
        GeneroLivro genero,
        @Positive(message = "O valor não pode ser negativo")
        BigDecimal preco,
        @UUID(message = "É preciso fornecer um id de autor válido")
        @NotBlank(message = "Campo obrigatório")
        String idAutor
) { }
