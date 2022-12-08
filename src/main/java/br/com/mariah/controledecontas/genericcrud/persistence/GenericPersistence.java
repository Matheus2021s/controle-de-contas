package br.com.mariah.controledecontas.genericcrud.persistence;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericPersistence<T extends GenericEntity> extends CollectionTarget {

    T save(T entity);

    void delete(T entity);

    T findById(T entity);

    Page<T> list(Pageable pageable);
}
