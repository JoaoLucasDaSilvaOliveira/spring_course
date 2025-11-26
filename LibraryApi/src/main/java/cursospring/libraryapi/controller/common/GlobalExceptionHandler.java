package cursospring.libraryapi.controller.common;

import cursospring.libraryapi.controller.dto.ErroCampoDTO;
import cursospring.libraryapi.controller.dto.ErroReposta;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroReposta hadleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampoDTO> listErrosCampo = fieldErrors
                .stream()
                .map(fieldError -> new ErroCampoDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return new ErroReposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", listErrosCampo);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroReposta handleAccessDeniedException (AccessDeniedException e){
        return new ErroReposta(HttpStatus.FORBIDDEN.value(), "Acesso Negado: "+ Arrays.toString(e.getStackTrace()), List.of());
    }
}
