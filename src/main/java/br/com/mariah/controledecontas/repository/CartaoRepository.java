package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
