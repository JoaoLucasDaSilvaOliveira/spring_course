package cursospring.arquiteturaspring.todos;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TodoService {

    //essa é uma forma de injetar dependencia sem usar o @Autowired. Forma mais recomendada
    private TodoRepository repository;
    private TodoValidator validator;

    public TodoService (TodoRepository todoRepository, TodoValidator validator){
        repository = todoRepository;
        this.validator = validator;
    }

    public TodoEntity salvar (TodoEntity todoEntity, boolean isUpdate){
        if (!isUpdate){
            validator.validar(todoEntity);
        }
        repository.save(todoEntity);
        return todoEntity;
    }

    public TodoEntity getTodoById (Integer id){
        return repository.findById(id).orElse(null);
    }
}
