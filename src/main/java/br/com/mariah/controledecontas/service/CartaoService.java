package br.com.mariah.controledecontas.service;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.CartaoPersistence;
import org.springframework.stereotype.Service;

@Service
public class CartaoService extends GenericCrudService{

    protected CartaoService(final CartaoPersistence persistence) {
        super(persistence, Cartao.class);
    }
}
