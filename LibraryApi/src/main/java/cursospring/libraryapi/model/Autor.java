package cursospring.libraryapi.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//aula de mapear as Entidade JPA
@Entity
@Table(name = "autor", schema = "public") // name: nome tabela | schema: nome do schema (padrão é public)
@Getter
@Setter //Lombok gera os getters e setters!
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Autor {

    public Autor (){}

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "nome",
            length = 100, // varchar (100)
            nullable = false //NOT NULL
    )
    private String nome;

    @Column(
            name = "data_nascimento",
            nullable = false
    )
    private LocalDate dataNascimento;

    @Column(
            nullable = false,
            length = 50
    )
    private String nacionalidade;

    @OneToMany(
            mappedBy = "Autor",
            cascade = CascadeType.ALL
    )// forma de mapear os livros que ele possui
    private List<Livro> livros;

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
    private LocalDateTime idUsuario;

}
