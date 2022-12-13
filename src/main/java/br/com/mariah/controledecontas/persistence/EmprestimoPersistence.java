package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Emprestimo;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.EmprestimoRepository;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoPersistence extends GenericCrudPersistence<Emprestimo, Long, EmprestimoRepository> {

    public EmprestimoPersistence(EmprestimoRepository repository) {
        super(Emprestimo.class, repository);
    }
}
