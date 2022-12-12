package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Fatura;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.FaturaRepository;
import org.springframework.stereotype.Component;

@Component
public class FaturaPersistence extends GenericCrudPersistence<Fatura,Long, FaturaRepository> {

    public FaturaPersistence(FaturaRepository repository) {
        super(repository);
    }
}
