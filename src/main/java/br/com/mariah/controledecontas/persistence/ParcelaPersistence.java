package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Parcela;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.ParcelaRepository;
import org.springframework.stereotype.Component;

@Component
public class ParcelaPersistence extends GenericCrudPersistence {

    public ParcelaPersistence(final ParcelaRepository repository) {
        super(Parcela.class, Long.class, repository);
    }
}
