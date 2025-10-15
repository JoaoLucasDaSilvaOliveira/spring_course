package cursospring.libraryapi.service;

import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AutorService {

    private AutorRepository repository;

    public void salvar(Autor autor){
        repository.save(autor);
    }

    public Autor obterPorId (UUID id){
        return repository.findById(id).orElseThrow();
    }

}
