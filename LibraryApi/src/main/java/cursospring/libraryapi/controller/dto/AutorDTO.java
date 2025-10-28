package cursospring.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter entre 3 e 100 caracteres", min = 3)
        String nome,
        @NotNull(message = "Data de nascimento é obrigatório")
        @Past(message = "Data de nascimento deve ser menor do que a data atual")
        LocalDate dataNascimento,
        @NotBlank(message = "Nacionalidade é obrigatório")
        @Size(max = 50, message = "Nacionalidade deve ter entre 2 e 50 caracteres", min = 2)
        String nacionalidade
) {
    //como fazer sem usar mapper
    /*public Autor mapearParaAutor(){
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

    public static Autor transferirDados(AutorDTO autorDTO, Autor autor){
        autor.setNome(autorDTO.nome());
        autor.setNacionalidade(autorDTO.nacionalidade());
        autor.setDataNascimento(autorDTO.dataNascimento());
        return autor;
    }*/
}
