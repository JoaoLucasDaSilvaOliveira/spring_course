package cursospring.libraryapi.service;

import cursospring.libraryapi.model.Autor;
import cursospring.libraryapi.model.GeneroLivro;
import cursospring.libraryapi.model.Livro;
import cursospring.libraryapi.repository.AutorRepository;
import cursospring.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class TransacaoService {

    /*@Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    Livro livro;

    @Autowired
    Autor autor;


    //transactional só funciona em métodos públicos
    @Transactional
    public void executarDandoCommit (){
        // salvar um livro
        //  será salvo com o autor //
        // salvar o autor
            Autor a = new Autor(
                    null,
                    "Robertin",
                    LocalDate.of(1999, 6, 30),
                    "Chinês",
                    new ArrayList<>(),
                    null,
                    null,
                    null
            );
            a.getLivros().add(new Livro(
                    null,
                    "978-85-253-0400-5",
                    "Aventuras de Tim",
                    LocalDate.of(2010, 8, 15),
                    GeneroLivro.BIOGRAFIA,
                    BigDecimal.valueOf(15.90),
                    a,
                    null,
                    null,
                    null
            ));
            autorRepository.save(a);
        // alugar o livro
        // enviar email para o locatario
        // notificar que o livro saiu da livraria
    }

    @Transactional
    public void executarDandoRollback (){
        Autor a = new Autor(
                null,
                "Julia",
                LocalDate.of(1999, 6, 30),
                "Japonesa",
                new ArrayList<>()
        );
        a.getLivros().add(new Livro(
                null,
                "978-85-253-0400-2",
                "Aventuras de TimTinha",
                LocalDate.of(2010, 8, 15),
                GeneroLivro.BIOGRAFIA,
                BigDecimal.valueOf(15.90),
                a
        ));
        autorRepository.save(a);
        //ao lançar um erro ocorre o rollback da transação
        throw new RuntimeException("Fazendo o rollback da transacao");

        //cancela pq o final da transação só ocorre ao final da transação (final da função)!
    }

    @Transactional
    public void executarDandoFlush (){
        Autor a = new Autor(
                null,
                "Robertinho",
                LocalDate.of(1999, 6, 30),
                "Chinês",
                new ArrayList<>()
        );
        a.getLivros().add(new Livro(
                null,
                "978-85-253-0400-4",
                "Aventuras de Tin",
                LocalDate.of(2010, 8, 15),
                GeneroLivro.BIOGRAFIA,
                BigDecimal.valueOf(15.90),
                a
        ));
        autorRepository.saveAndFlush(a);
        //ao lançar um erro ocorre o rollback da transação
        throw new RuntimeException("Fazendo o rollback da transacao com flush");

        //envia os comando ao banco de dados, mas não as executa ainda, somente no commit!
        //cancela pq o final da transação só ocorre ao final da transação (final da função)!
    }

    */

    // OBS!!! Ao atualizar um objeto vindo do banco (UPDATE), não precisa salvar dnovo pois o conceito de transação compreende isso.
    /** Ex.:
     * PEGA O/OS DADO/S DO BANCO
     * var response = repository.findById(id);
     * MUDA O QUE FOR NECESSÁRIO
     * response.setTitulo("Novo Título");
     * NORMALMENTE PRECISARIA FAZER O repository.save(response); MAS NESSE CASO COMO EU TENHO UMA TRANSAÇÃO ATIVA, ELE ENTRA DE ESTADO DE MANAGED PRA O ESTADO DO BANCO AUTOMATICAMENTE
     */


}
