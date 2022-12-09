package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaRepository extends JpaRepository<Parcela,Long> {
}
