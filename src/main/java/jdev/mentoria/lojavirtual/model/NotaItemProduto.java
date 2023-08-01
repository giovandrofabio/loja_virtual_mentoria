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
@Table(name="nota_item_produto")
@SequenceGenerator(name="seq_nota_item_produto", sequenceName ="seq_nota_item_produto", allocationSize = 1, initialValue = 1)
public class NotaItemProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_item_produto")
    private Long id;

    @Column(nullable = false)
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_compra_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_compra_fk"))
    private NotaFiscalCompra notaFiscalCompra;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
    private Produto produto;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private Pessoa empresa;
}
