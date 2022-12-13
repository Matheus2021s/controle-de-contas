package br.com.mariah.controledecontas.genericcrud.collection.persistence;

import br.com.mariah.controledecontas.genericcrud.collection.Collection;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersistenceCollection extends Collection<GenericCrudPersistence> {


    public PersistenceCollection(final List<GenericCrudPersistence> list) {
        super(list);
    }

    public GenericCrudPersistence resolveByEntity(Class<?> type) {
        return getList().stream().filter(genericCrudPersistence -> genericCrudPersistence.getEntityClass().equals(type))
                .findFirst().orElse(null);
    }
}
