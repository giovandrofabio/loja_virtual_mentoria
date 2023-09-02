package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.AccessTokenJunoAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccesTokenJunoRepository extends JpaRepository<AccessTokenJunoAPI, Long> {
}
