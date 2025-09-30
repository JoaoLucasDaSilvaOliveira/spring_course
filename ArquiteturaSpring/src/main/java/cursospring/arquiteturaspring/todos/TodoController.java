package cursospring.arquiteturaspring.todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService todoService){
        service = todoService;
    }

    @PostMapping
    public TodoEntity salvarTodo (@RequestBody TodoEntity todoEntity){
        try {
            return service.salvar(todoEntity, false);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public TodoEntity mudarEstado (@PathVariable Integer id){
        TodoEntity todo = service.getTodoById(id);
        if (todo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element with this id");
        }
        todo.setConcluido(!todo.isConcluido());
        return service.salvar(todo, true);
    }
}
