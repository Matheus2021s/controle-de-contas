package br.com.mariah.controledecontas.service;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.BancoPersistence;
import org.springframework.stereotype.Service;

@Service
public class BancoService extends GenericCrudService{

    protected BancoService(final BancoPersistence persistence) {
        super(persistence, Banco.class);
    }


}
