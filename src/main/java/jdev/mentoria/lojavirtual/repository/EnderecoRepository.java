package jdev.mentoria.lojavirtual.repository;

import jdev.mentoria.lojavirtual.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
