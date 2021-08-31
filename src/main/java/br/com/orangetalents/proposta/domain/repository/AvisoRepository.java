package br.com.orangetalents.proposta.domain.repository;

import br.com.orangetalents.proposta.domain.modelo.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisoRepository extends JpaRepository<Aviso, Long> {
}
