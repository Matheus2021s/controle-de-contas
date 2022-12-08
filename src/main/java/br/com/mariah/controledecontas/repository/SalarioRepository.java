package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Salario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarioRepository extends JpaRepository<Salario, Long> {
}
