package jdev.mentoria.lojavirtual.dto;

import jdev.mentoria.lojavirtual.model.Produto;
import lombok.Getter;
import lombok.Setter;

public class ItemVendaDTO {

    private Double quantidade;

    private ProdutoDTO produto = new ProdutoDTO();
//    private Produto produto;

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }
}
