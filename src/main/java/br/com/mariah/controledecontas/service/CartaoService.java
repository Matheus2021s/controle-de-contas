package br.com.mariah.controledecontas.service;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.genericcrud.service.DependecyResolver;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.CartaoPersistence;
import br.com.mariah.controledecontas.repository.CartaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CartaoService extends GenericCrudService<Cartao, Long, CartaoRepository, CartaoPersistence> {

    protected CartaoService(CartaoPersistence persistence, DependecyResolver dependecyResolver) {
        super(persistence, dependecyResolver, Cartao.class);
    }
}
