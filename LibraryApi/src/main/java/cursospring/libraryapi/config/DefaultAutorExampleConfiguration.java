package cursospring.libraryapi.config;

import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
public class DefaultAutorExampleConfiguration {

    @Autowired
    Livro livro;

    @Bean
    public Autor autor () {

        Autor autor = new Autor(
                null,
                "Jose Ruela",
                LocalDate.of(2000, 12, 15),
                "Brasileiro",
                new ArrayList<>(),
                null,
                null,
                null
        );
        livro.setAutor(autor);
        autor.getLivros().add(livro);
        return autor;
    }
}
