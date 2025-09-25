package cursoSpring.ProdutosApi.controller;

import cursoSpring.ProdutosApi.model.Produto;
import cursoSpring.ProdutosApi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    //retornando nada
    /*@PostMapping
    public void salvar(@RequestBody Produto produto) {
        System.out.println("Produto salvo: " + produto);
    }*/

    //AULA 04 - CONFIGURANDO O OBJETO REPOSITORY
    private final ProdutoRepository produtoRepository;

    //retornando um JSON do produto
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        produto.setId(UUID.randomUUID().toString());
        System.out.println("Produto salvo: " + produto);
        produtoRepository.save(produto);
        return produto;
    }

    //AULA 05 - CRIANDO A ROTA GET
    @GetMapping("/{id}")
    public Produto obterPorId (@PathVariable("id") String id){
        return produtoRepository.findById(id).orElse(null);
    }

    //AULA 06 - CRIANDO A ROTA DELETE
    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable String id){
        produtoRepository.deleteById(id);
    }

    //AULA 07 - CRIANDO A ROTA PUT
    @PutMapping("/{id}")
    public void atualizarPorId(@PathVariable String id, @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto); //o métod-o save faz o put tb se já houver um objeto com o mesmo identificador
    }
    // AULA 08 - CRIANDO A PROCURA POR NOME
    @GetMapping
    public List<Produto> buscarPorNome (@RequestParam String nome){
        return produtoRepository.getByNome(nome);
    }
}
