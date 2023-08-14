package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.MarcaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MarcaRepository extends JpaRepository<MarcaProduto, Long> {

    @Query("select a from MarcaProduto a where upper(trim(a.nomeDesc)) like %?1%")
    List<MarcaProduto> buscarMarcaDesc(String desc);
}
