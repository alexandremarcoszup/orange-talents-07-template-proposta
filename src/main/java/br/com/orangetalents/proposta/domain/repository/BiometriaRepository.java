package br.com.orangetalents.proposta.domain.repository;

import br.com.orangetalents.proposta.domain.modelo.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
