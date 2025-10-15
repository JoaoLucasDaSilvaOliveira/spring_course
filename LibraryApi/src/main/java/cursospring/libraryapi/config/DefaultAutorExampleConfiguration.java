package cursospring.libraryapi.config;

import cursospring.libraryapi.model.Autor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class DefaultAutorExampleConfiguration {
    /*@Bean
    public Autor autor () {
        return new Autor(
                null,
                "Jose Ruela",
                LocalDate.of(2000, 12, 15),
                "Brasileiro",
                new ArrayList<>()
        );
    }*/
}
