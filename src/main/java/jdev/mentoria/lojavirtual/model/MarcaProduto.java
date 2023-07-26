package jdev.mentoria.lojavirtual.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeDesc() {
        return nomeDesc;
    }
    public void setNomeDesc(String nomeDesc) {
        this.nomeDesc = nomeDesc;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarcaProduto)) return false;
        MarcaProduto that = (MarcaProduto) o;
        return getId().equals(that.getId()) && Objects.equals(getNomeDesc(), that.getNomeDesc());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNomeDesc());
    }
}
