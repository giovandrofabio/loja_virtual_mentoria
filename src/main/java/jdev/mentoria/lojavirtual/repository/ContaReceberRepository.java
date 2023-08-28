package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.ContaReceber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {
}
