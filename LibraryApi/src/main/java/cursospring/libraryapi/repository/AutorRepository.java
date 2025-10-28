package cursospring.libraryapi.repository;

import cursospring.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//TRABALHANDO COM REPOS
public interface AutorRepository extends JpaRepository<Autor, UUID> {
    List<Autor> getAutorById(UUID id);

    Autor getAutorByNome(String nome);

    List<Autor> findAutorByNome(String nome);

    List<Autor> findAutorByNomeIgnoreCaseOrNacionalidadeIgnoreCase(String nome, String nacionalidade);

    List<Autor> findAutorByNomeIgnoreCaseAndNacionalidadeIgnoreCaseAndDataNascimento(String nome, String nacionalidade, LocalDate dataNascimento);
}
