package br.com.mariah.controledecontas.service;

import br.com.mariah.controledecontas.domain.Emprestimo;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.EmprestimoPersistence;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoService extends GenericCrudService {

    protected EmprestimoService(final EmprestimoPersistence persistence) {
        super(persistence, Emprestimo.class);
    }
}
