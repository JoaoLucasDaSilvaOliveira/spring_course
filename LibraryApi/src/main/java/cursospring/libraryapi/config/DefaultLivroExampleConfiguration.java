package cursospring.libraryapi.config;

import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import cursospring.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Configuration
public class DefaultLivroExampleConfiguration {

    /*@Autowired
    AutorRepository repository;
    private String authorName = "Sr Jose Ruela";

    @Bean
    public Livro livro (){
        Autor autor = repository.findAutorByNome(authorName).getFirst();
        if(autor != null){
            return new Livro(
                    null,
                    "978-85-333-0400-5",
                    "O Pequeno Príncipe",
                    LocalDate.of(2015, 5, 10),
                    GeneroLivro.FANTASIA,
                    BigDecimal.valueOf(59.90),
                    autor
            );
        }
        throw new NoSuchElementException("Não há nenhum autor cadastrado com esse nome: "+authorName);
    }*/

}
