package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
