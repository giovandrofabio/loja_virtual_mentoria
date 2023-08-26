package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.StatusRastreio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRastreioRepository extends JpaRepository<StatusRastreio, Long> {
}
