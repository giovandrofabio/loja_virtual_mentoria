package jdev.mentoria.lojavirtual.dto;

import jdev.mentoria.lojavirtual.enums.StatusContaPagar;

import java.math.BigDecimal;
import java.util.Date;

public class ContaPagarDto {

    private Long id;

    private String descricao;

    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    private StatusContaPagar status;

    private Date dtVencimento;

    private Date dtPagamento;

    private String pessoa;

    private String pessoa_fornecedor;

    private String empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public StatusContaPagar getStatus() {
        return status;
    }

    public void setStatus(StatusContaPagar status) {
        this.status = status;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getPessoa_fornecedor() {
        return pessoa_fornecedor;
    }

    public void setPessoa_fornecedor(String pessoa_fornecedor) {
        this.pessoa_fornecedor = pessoa_fornecedor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
