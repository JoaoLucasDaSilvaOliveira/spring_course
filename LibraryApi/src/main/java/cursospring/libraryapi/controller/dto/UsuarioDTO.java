package cursospring.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UsuarioDTO(
        @NotBlank
        String login,
        @NotBlank
        String senha,
        @NotNull
        List<String> roles,
        @Email (message = "É preciso fornecer um email válido")
        @NotBlank
        String email
) {
}
