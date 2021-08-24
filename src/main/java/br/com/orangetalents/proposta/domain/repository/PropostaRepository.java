package br.com.orangetalents.proposta.domain.repository;

import br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus;
import br.com.orangetalents.proposta.domain.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findAllByStatus(SolicitacaoStatus status);
}
