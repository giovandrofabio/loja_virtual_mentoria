package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.dto.PessoaJuridicaDto;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesssoaRepository extends CrudRepository<PessoaJuridica, Long> {

    @Query(value = "select pj from PessoaJuridica pj where upper(trim(pj.nome)) like %?1%")
    public List<PessoaJuridica> pesquisaPorNomePJ(String nome);

    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    public PessoaJuridica existeCnpjCadastrado(String cnpj);

    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    public List<PessoaJuridica> existeCnpjCadastradoList(String cnpj);


    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    public PessoaFisica existeCpfCadastrado(String cpf);


    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    public List<PessoaFisica> existeCpfCadastradoList(String cpf);


    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    public PessoaJuridica existeInsEstadualCadastrado(String inscEstadual);


    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    public List<PessoaJuridica> existeInsEstadualCadastradoList(String inscEstadual);

    @Query(value = "select * from pessoa_juridica pj where pj.id = :id", nativeQuery = true)
    public PessoaJuridica buscaPorId(Long id);
}
