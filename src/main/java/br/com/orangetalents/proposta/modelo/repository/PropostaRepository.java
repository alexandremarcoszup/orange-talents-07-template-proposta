package br.com.orangetalents.proposta.modelo.repository;

import br.com.orangetalents.proposta.modelo.domain.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
