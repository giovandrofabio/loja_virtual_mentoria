package jdev.mentoria.lojavirtual.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="item_venda_loja")
@SequenceGenerator(name="seq_item_venda_loja", sequenceName ="seq_item_venda_loja", allocationSize = 1, initialValue = 1)
public class ItemVendaLoja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venda_loja")
    private Long id;
    @Column(nullable = false)
    private Double quantidade;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "venda_compraLoja_virtu_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compraLoja_virtu_fk"))
    private VendaCompraLojaVirtual vendaCompraLojaVirtual;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public VendaCompraLojaVirtual getVendaCompraLojaVirtual() {
        return vendaCompraLojaVirtual;
    }
    public void setVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
        this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVendaLoja)) return false;
        ItemVendaLoja that = (ItemVendaLoja) o;
        return getId().equals(that.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
