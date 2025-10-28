package cursospring.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroReposta(int status, String mensagem, List<ErroCampoDTO> erros){
    //repostas comuns
    public static ErroReposta duplicado (String mensagem){
        return new ErroReposta(409, mensagem, List.of());
    }

    public static ErroReposta idIncorreto (List<ErroCampoDTO> erros){
        return new ErroReposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", erros);
    }
}
