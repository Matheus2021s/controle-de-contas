package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.CartaoRepository;
import org.springframework.stereotype.Component;

@Component
public class CartaoPersistence extends GenericCrudPersistence {

    public CartaoPersistence(final CartaoRepository repository) {
        super(Cartao.class, Long.class, repository);
    }
}
