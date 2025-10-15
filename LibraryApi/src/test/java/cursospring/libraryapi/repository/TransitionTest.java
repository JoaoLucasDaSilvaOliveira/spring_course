package cursospring.libraryapi.repository;

import cursospring.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransitionTest {

    @Autowired
    TransacaoService service;

    @Test
    /***
     * commit -> confirmar alterações
     * rollback -> voltar nas alterações
     */
    void transacaoSimples(){
        service.executarDandoCommit();
    }

    @Test
    void transacaoComErro(){
        service.executarDandoRollback();
    }

    @Test
    void transacaoComFlush(){
        service.executarDandoFlush();
    }

}
