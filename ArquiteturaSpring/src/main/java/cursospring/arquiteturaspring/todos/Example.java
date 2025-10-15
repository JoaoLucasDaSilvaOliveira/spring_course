package cursospring.arquiteturaspring.todos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Example {
    //exatamente da mesma forma que funciona com um arquivo .env
    @Value("${app.exemple.var.value}")
    private String varProp;

    public void imprimirVar (){
        System.out.println(varProp);
    }
}
