package jdev.mentoria.lojavirtual.model;

import jdev.mentoria.lojavirtual.enums.StatusContaPagar;
import jdev.mentoria.lojavirtual.enums.StatusContaReceber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="conta_pagar")
@SequenceGenerator(name="seq_conta_pagar", sequenceName ="seq_conta_pagar", allocationSize = 1, initialValue = 1)
public class ContaPagar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusContaPagar status;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtVencimento;
    @Temporal(TemporalType.DATE)
    private Date dtPagamento;
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_forn_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_forn_fk"))
    private Pessoa pessoa_fornecedor;
}
