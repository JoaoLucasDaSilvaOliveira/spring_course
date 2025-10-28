package cursospring.libraryapi.controller.mappers;

import cursospring.libraryapi.controller.dto.AtualizarLivroDTO;
import cursospring.libraryapi.controller.dto.DetalhesLivroDTO;
import cursospring.libraryapi.controller.dto.LivroDTO;
import cursospring.libraryapi.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro deDTOPraLivro (LivroDTO dto);

    LivroDTO deLivroPraDTO (Livro livro);

    DetalhesLivroDTO detalhesDoLivro(Livro livro);

    LivroDTO deAtualizarPraDTO(AtualizarLivroDTO atDTO);

    Livro deAtualizarParaLivro (AtualizarLivroDTO dto);

}
