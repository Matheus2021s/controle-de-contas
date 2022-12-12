package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Parcela;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.ParcelaRepository;
import org.springframework.stereotype.Component;

@Component
public class ParcelaPersistence extends GenericCrudPersistence<Parcela,Long, ParcelaRepository> {

    public ParcelaPersistence(final ParcelaRepository repository) {
        super(repository);
    }
}
