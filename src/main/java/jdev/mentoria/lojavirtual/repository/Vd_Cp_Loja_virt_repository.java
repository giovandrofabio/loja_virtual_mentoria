package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface Vd_Cp_Loja_virt_repository extends JpaRepository<VendaCompraLojaVirtual, Long> {

    @Query(nativeQuery = true, value = "select * from vd_cp_loja_virt where id = ?1")
    public VendaCompraLojaVirtual BuscaporId(Long id);

    @Query(value="select a from VendaCompraLojaVirtual a where a.id = ?1 and a.excluido = false")
    VendaCompraLojaVirtual findByIdExclusao(Long id);

    @Query(value="select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")
    List<VendaCompraLojaVirtual> vendaPorProduto(Long idProduto);
    @Query(value="select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i "
            + " where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.produto.nome)) like %?1%")
    List<VendaCompraLojaVirtual> vendaPorNomeProduto(String valor);

    @Query(value="select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i "
            + " where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vendaCompraLojaVirtual.pessoa.nome)) like %?1%")
    List<VendaCompraLojaVirtual> vendaPorNomeCliente(String nomepessoa);

    @Query(value="select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i "
            + " where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vendaCompraLojaVirtual.enderecoCobranca.ruaLogra)) "
            + " like %?1%")
    List<VendaCompraLojaVirtual> vendaPorEndereCobranca(String enderecocobranca);

    @Query(value="select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i "
            + " where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vendaCompraLojaVirtual.enderecoEntrega.ruaLogra)) "
            + " like %?1%")
    List<VendaCompraLojaVirtual> vendaPorEnderecoEntrega(String enderecoentrega);
}
