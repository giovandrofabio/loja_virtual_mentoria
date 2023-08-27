package jdev.mentoria.lojavirtual.service;

import jdev.mentoria.lojavirtual.model.VendaCompraLojaVirtual;
import jdev.mentoria.lojavirtual.repository.Vd_Cp_Loja_virt_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Vd_Cp_Loja_virt_repository vd_Cp_Loja_virt_repository;

    public void exclusaoTotalVendaBanco2(Long idVenda) {
        String sql = "begin; update vd_cp_loja_virt set excluido = true where id = " + idVenda +"; commit;";
        jdbcTemplate.execute(sql);;
    }
    public void exclusaoTotalVendaBanco(Long idVenda) {

        String value =
                " begin;"
                        + " UPDATE nota_fiscal_venda set venda_compra_loja_virt_id = null where venda_compra_loja_virt_id = "+idVenda+"; "
                        + " delete from nota_fiscal_venda where venda_compra_loja_virt_id = "+idVenda+"; "
                        + " delete from item_venda_loja where venda_compra_loja_virtu_id = "+idVenda+"; "
                        + " delete from status_rastreio where venda_compra_loja_virt_id = "+idVenda+"; "
                        + " delete from vd_cp_loja_virt where id = "+idVenda+"; "
                        + " commit; ";

        jdbcTemplate.execute(value);
    }

    public void ativaRegistroVendaBanco(Long idVenda) {
        String sql = "begin; update vd_cp_loja_virt set excluido = false where id = " + idVenda +"; commit;";
        jdbcTemplate.execute(sql);;

    }

    /*HQL (Hibernate) ou JPQL (JPA ou Spring Data)*/
    public List<VendaCompraLojaVirtual> consultaVendaFaixaData(String data1, String data2) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = dateFormat.parse(data1);
        Date date2 = dateFormat.parse(data2);

//        String sql = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i "
//                + " where i.vendaCompraLojaVirtual.excluido = false "
//                + " and i.vendaCompraLojaVirtual.dataVenda >= '" + data1 + "'"
//                + " and i.vendaCompraLojaVirtual.dataVenda <= '" + data2 + "'";

        // return entityManager.createQuery(sql).getResultList();

        return vd_Cp_Loja_virt_repository.consultaVendaFaixaData(date1, date2);

    }
}
