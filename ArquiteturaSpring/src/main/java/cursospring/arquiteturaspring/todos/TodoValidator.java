package cursospring.arquiteturaspring.todos;
import org.springframework.stereotype.Component;

//como a classe não será um Service, Repository ou Controller, nomeamos como Component. Uma forma mais genérica, será apenas um componente da aplicação
@Component
public class TodoValidator {

    private TodoRepository todoRepository;

    public TodoValidator (TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public void validar (TodoEntity todoEntity){
        if (todoRepository.existsByDescricaoIgnoreCase(todoEntity.getDescricao())){
            throw new IllegalArgumentException("Já existe um TODO com essa descrição!");
        }
    }
}
