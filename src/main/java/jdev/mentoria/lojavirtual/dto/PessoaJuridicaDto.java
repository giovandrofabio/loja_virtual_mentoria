package jdev.mentoria.lojavirtual.dto;

import jdev.mentoria.lojavirtual.model.Endereco;
import jdev.mentoria.lojavirtual.model.Pessoa;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDto {

//    private Long id;

//    private String nome;

//    private String email;
//
//    private String telefone;
//
//    private String tipoPessoa;

//    private List<Endereco> enderecos = new ArrayList<Endereco>();
//
//    private Pessoa empresa;

//    private String cnpj;
//
//    private String inscEstadual;
//
//    private String inscMunicipal;
//
    private String nomeFantasia;
//
//    private String razaoSocial;
//
//    private String categoria;

    public PessoaJuridicaDto(PessoaJuridica pessoaJuridica) {
        this.nomeFantasia = pessoaJuridica.getNomeFantasia();
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public String getTipoPessoa() {
//        return tipoPessoa;
//    }
//
//    public void setTipoPessoa(String tipoPessoa) {
//        this.tipoPessoa = tipoPessoa;
//    }

//    public List<Endereco> getEnderecos() {
//        return enderecos;
//    }
//
//    public void setEnderecos(List<Endereco> enderecos) {
//        this.enderecos = enderecos;
//    }
//
//    public Pessoa getEmpresa() {
//        return empresa;
//    }
//
//    public void setEmpresa(Pessoa empresa) {
//        this.empresa = empresa;
//    }
//
//    public String getCnpj() {
//        return cnpj;
//    }
//
//    public void setCnpj(String cnpj) {
//        this.cnpj = cnpj;
//    }
//
//    public String getInscEstadual() {
//        return inscEstadual;
//    }
//
//    public void setInscEstadual(String inscEstadual) {
//        this.inscEstadual = inscEstadual;
//    }
//
//    public String getInscMunicipal() {
//        return inscMunicipal;
//    }
//
//    public void setInscMunicipal(String inscMunicipal) {
//        this.inscMunicipal = inscMunicipal;
//    }
//
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
//
//    public String getRazaoSocial() {
//        return razaoSocial;
//    }
//
//    public void setRazaoSocial(String razaoSocial) {
//        this.razaoSocial = razaoSocial;
//    }
//
//    public String getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(String categoria) {
//        this.categoria = categoria;
//    }
}
