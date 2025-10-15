package cursospring.libraryapi.repository;

import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    Livro livro;

    @Autowired
    Autor autor;

    @Test
    public void saveExampleAuthor (){
        repository.save(autor);
    }

    @Test
    public void atualizarAutor (){
        Autor autorByName = repository.getAutorByNome("Jose Ruela");

        autorByName.setNome("Sr Jose Ruela");

        repository.save(autorByName);
    }

    @Test
    public void showAll (){
        repository.findAll().forEach(System.out::println);
    }

    @Test
    public void countTest (){
        System.out.println(repository.count());
    }

    @Test
    public void deleteTest(){
        Autor autorByName = repository.getAutorByNome("Sr Jose Ruela");

        repository.save(autorByName);
    }

    @Test
    public void updateAutor(){
        Autor autor = repository.getAutorByNome("Sr Jose Ruela");
        if (autor != null){
            autor.setNacionalidade("Boliviano");
            repository.save(autor);
        }
    }

    @Test
    public void saveAutorComLivro(){
        autor.setNome("Bizarro da silva");
        autor.setDataNascimento(autor.getDataNascimento().withYear(2004));
        autor.setNacionalidade("Australiano");

        livro.setAutor(autor);
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("As aventuras do BOPE");
        livro.setIsbn(livro.getIsbn().replace("5", "2"));

        autor.getLivros().add(livro);

        repository.save(autor);
        //aqui, caso não tivesse o cascade.ALL para salvar os livros quando salvar o autor, precisaria fazer assim
        /*
        livroRepository.save(autor.getLivros());
         */
    }

    @Test
    @Transactional
    public void verLivros(){
        Autor autor = repository.findAutorByNome("Sr Jose Ruela").getFirst();
        System.out.println(autor.getLivros());
    }
}
