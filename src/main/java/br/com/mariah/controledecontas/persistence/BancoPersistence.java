package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.BancoRepository;
import org.springframework.stereotype.Component;

@Component
public class BancoPersistence extends GenericCrudPersistence<Banco, Long, BancoRepository> {
    public BancoPersistence(final BancoRepository repository) {
        super(repository);
    }


}
