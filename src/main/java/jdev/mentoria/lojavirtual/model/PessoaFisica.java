package jdev.mentoria.lojavirtual.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Pessoa{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
}
