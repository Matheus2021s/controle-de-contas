package br.com.mariah.controledecontas.genericcrud.collection.persistence;

import br.com.mariah.controledecontas.genericcrud.collection.Collection;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersistenceCollection extends Collection<GenericPersistence> {

    private List<GenericPersistence> genericPersistences;

    public PersistenceCollection(final List<GenericPersistence> genericPersistences) {
        super(genericPersistences);
        this.genericPersistences = genericPersistences;
    }

}
