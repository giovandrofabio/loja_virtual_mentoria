package jdev.mentoria.lojavirtual.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="form_pagamento")
@SequenceGenerator(name="seq_form_pagamento", sequenceName ="seq_form_pagamento", allocationSize = 1, initialValue = 1)
public class FormaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_form_pagamento")
    private Long id;

    @NotNull(message = "Descrição deve ser informada")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "A empresa deve ser informada")
    @ManyToOne(targetEntity = PessoaJuridica.class)
    @JoinColumn(name = "empresa_id", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private PessoaJuridica empresa;
}
