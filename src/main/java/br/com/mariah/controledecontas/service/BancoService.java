package br.com.mariah.controledecontas.service;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.genericcrud.service.DependecyResolver;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.BancoPersistence;
import br.com.mariah.controledecontas.repository.BancoRepository;
import org.springframework.stereotype.Service;

@Service
public class BancoService extends GenericCrudService<Banco, Long, BancoRepository, BancoPersistence> {

    protected BancoService(final BancoPersistence persistence, final DependecyResolver dependecyResolver) {
        super(persistence, dependecyResolver, Banco.class);
    }


}
