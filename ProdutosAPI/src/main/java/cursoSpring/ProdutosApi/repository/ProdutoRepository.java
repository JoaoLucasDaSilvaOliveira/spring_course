package cursoSpring.ProdutosApi.repository;

import cursoSpring.ProdutosApi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> getByNome(String nome);
}
