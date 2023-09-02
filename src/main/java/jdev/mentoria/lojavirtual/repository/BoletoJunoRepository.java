package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.BoletoJuno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoJunoRepository extends JpaRepository<BoletoJuno, Long> {
}
