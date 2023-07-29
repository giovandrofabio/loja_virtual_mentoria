package jdev.mentoria.lojavirtual.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="marca_produto")
@SequenceGenerator(name="seq_marca_produto", sequenceName ="seq_marca_produto", allocationSize = 1, initialValue = 1)
public class MarcaProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_produto")
    private Long id;
    @Column(nullable = false)
    private String nomeDesc;
}
