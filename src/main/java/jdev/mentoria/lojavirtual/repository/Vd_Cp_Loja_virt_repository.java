package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface Vd_Cp_Loja_virt_repository extends JpaRepository<VendaCompraLojaVirtual, Long> {

    @Query(nativeQuery = true, value = "select * from vd_cp_loja_virt where id = ?1")
    public VendaCompraLojaVirtual BuscaporId(Long id);
}
