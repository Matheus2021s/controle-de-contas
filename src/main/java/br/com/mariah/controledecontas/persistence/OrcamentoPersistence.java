package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Orcamento;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoPersistence extends GenericCrudPersistence<Orcamento,Long,OrcamentoRepository> {

    public OrcamentoPersistence(final OrcamentoRepository repository) {
        super(repository);
    }
}
