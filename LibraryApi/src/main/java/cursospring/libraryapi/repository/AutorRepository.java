package cursospring.libraryapi.repository;

import cursospring.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//TRABALHANDO COM REPOS
public interface AutorRepository extends JpaRepository<Autor, UUID> {
    List<Autor> getAutorById(UUID id);

    Autor getAutorByNome(String nome);

    List<Autor> findAutorByNome(String nome);
}
