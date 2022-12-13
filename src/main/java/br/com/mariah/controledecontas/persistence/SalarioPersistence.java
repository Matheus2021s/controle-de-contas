package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Salario;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.SalarioRepository;
import org.springframework.stereotype.Component;


@Component
public class SalarioPersistence extends GenericCrudPersistence<Salario, Long, SalarioRepository> {

    public SalarioPersistence(SalarioRepository repository) {
        super(Salario.class, repository);
    }
}
