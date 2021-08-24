package br.com.orangetalents.proposta.domain.repository;

import br.com.orangetalents.proposta.domain.modelo.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}
