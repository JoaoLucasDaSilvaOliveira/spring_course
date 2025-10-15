package cursospring.libraryapi.repository;

import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    Livro livro;

    @Test
    public void saveLivro (){
        repository.save(livro);
    }

    @Test
    public void saveLivroCascade (){
        Autor autor = new Autor(
                null,
                "Maria",
                LocalDate.of(2004, 10, 10),
                "Estadounidense",
                null
        );
        //observe que com o cascade, eu não vou salvar no banco primeiro o autor e depois passar para o livro
        repository.save(new Livro(
                null,
                "978-85-333-0400-4",
                "Another Book",
                LocalDate.of(2012, 12, 12),
                GeneroLivro.CIENCIA,
                BigDecimal.valueOf(99.90),
                autor
        ));
    }

    @Test
    @Transactional // sem essa annotation, quando temos o fecth = LAZY, não puxaria os dados do autor
    // já com essa annotation criamos uma transação no banco, podendo acessar o objeto várias vezes dentro da transação
    public void buscarLivroTest (){
        Livro livro = repository.findLivroByTitulo("Outro livro");
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void queryPesornalizada(){
        repository.listarLivroOrdenadoPorPrecoETitulo();
    }

    @Test
    void queryPersonalizadaBuscaPorTituloSemCase(){
        repository.listarLivrosPorTituloSemCase("prí").forEach(System.out::println);
    }

    @Test
    void queryPersonalizadaBuscaPorGenero(){
        repository.listarLivrosPorGenero(GeneroLivro.CIENCIA).forEach(System.out::println);
    }

    @Test
    void queryPersonalizadaBuscaPorGeneroOrdenada(){
        repository.listarLivrosPorGeneroOrdenado(GeneroLivro.CIENCIA, "dataPublicacao").forEach(System.out::println);
    }
}
