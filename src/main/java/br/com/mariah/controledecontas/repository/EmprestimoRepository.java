package br.com.mariah.controledecontas.repository;

import br.com.mariah.controledecontas.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
}
