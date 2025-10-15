package cursospring.libraryapi;

import cursospring.libraryapi.config.DefaultAutorExampleConfiguration;
import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApiApplication.class, args);
    }

}
