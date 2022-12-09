package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
}
