package cursospring.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data// compreende @Getter; @Setter; @ToString; @EqualsAndHashCode; @RequiredArgsConstructor
@AllArgsConstructor // cria um construtor que recebe todos os args
@EntityListeners(AuditingEntityListener.class)
public class Livro {

    public Livro (){}

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            unique = true,
            length = 20,
            nullable = false
    )
    private String isbn;

    @Column(
            length = 20,
            nullable = false
    )
    private String titulo;

    @Column(
            name = "data_publicacao",
            nullable = false
    )
    private LocalDate dataPublicacao;

    @Column(
            length = 30,
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private GeneroLivro genero;

    @Column(
            precision = 18,
            scale = 2, // numeric (18,2)
            nullable = false
    )
    private BigDecimal preco;

    @ManyToOne(
    //        cascade = CascadeType.ALL, //cascade cria/delete/updata o relacionamento no banco junto na criação do obj relacionado
            //fetch = FetchType.LAZY //padrão é EAGER. Lazy não traz os dados do relacionamento nas consultas
    )
    @JoinColumn(name = "id_autor")
    private Autor Autor;

    @Column(
            name = "data_cadastro"
    )
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(
            name = "data_atualizacao"
    )
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @Column(
            name = "id_usuario"
    )
    private UUID idUsuario;

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", genero=" + genero +
                ", preco=" + preco +
                ", Autor=" + Autor.getNome() +
                '}';
    }
}
