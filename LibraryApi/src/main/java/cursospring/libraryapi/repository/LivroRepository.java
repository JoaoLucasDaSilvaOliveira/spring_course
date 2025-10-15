package cursospring.libraryapi.repository;

import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    Livro findLivroByTitulo(String titulo);

    //JPQL - LINGUAGEM SQL DO JPA
    //query sem parametros
    @Query(value = "SELECT l FROM Livro l ORDER BY l.titulo, l.preco")
    /***
     * @see LivroRepositoryTest.queryPesornalizada();
     */
    List<Livro> listarLivroOrdenadoPorPrecoETitulo();

    //query com parametros
    // Named Parameters: nomeamos os parametros como se fosse variáveis (utiliza-se a seguinte anotação para se referir a parametros -> :nomeDoParam)
    //  OBSERVE: aqui foi utilizado o nativeQuery = true. O default é false, observe o ex seguinte
        @Query(
                value = """
                    SELECT * FROM Livro l WHERE l.titulo ILIKE %:titulo%
    """,
                nativeQuery = true
        )
    List<Livro> listarLivrosPorTituloSemCase (@Param(value = "titulo") String titulo);

    /* OBSERVE: nesse caso aqui nós não declaramos nativeQuery como true, então utilizamos a linguagem do JPQL onde:
        - Nomes das entidades são os mesmos que as Classes
        - Nomes dos colunas são os mesmos que os atributos
        - * não é reconhecido, precisa de um alias (as)
     */
    @Query(
            value = """
                SELECT l FROM Livro AS l WHERE l.genero =:genero
    """)
    List<Livro> listarLivrosPorGenero (@Param(value = "genero") GeneroLivro generoLivro);

    //Positional Parameters: nesse caso não os nomeamos e sim declaramos as posições
    // - Começa em 1
    // - Utiliza a ordem dos parametros da função
    @Query (
            value = """
                SELECT l FROM Livro l WHERE l.genero = ?1 ORDER BY ?2
            """
    )
    List<Livro> listarLivrosPorGeneroOrdenado (GeneroLivro generoLivro, String nomeOrdenacao);

    //OBS!!!: Para ações de escrita no banco, precisa, além do @Query, de mais duas annotations: @Modifying e @Transactional.
    //  - @Modifying: sinaliza que uma modificação ocorrerá
    //  - @Transactional: inicial uma transaction no banco (transação)


}
