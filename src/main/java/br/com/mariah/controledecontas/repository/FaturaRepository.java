package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {
}
