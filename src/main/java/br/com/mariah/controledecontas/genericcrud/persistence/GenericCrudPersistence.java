package br.com.mariah.controledecontas.genericcrud.persistence;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericCrudPersistence<T extends GenericEntity, ID, R extends JpaRepository<T, ID>> implements CollectionTarget {

    @Getter
    private final Class<T> entityClass;
    private final Class<ID> parameterIdType;
    private final R repository;
    protected GenericCrudPersistence(Class<T> entityClass, Class<ID> parameterIdType, R jpaRepository) {
        this.repository = jpaRepository;
        this.parameterIdType = parameterIdType;
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        return this.repository.save(entity);
    }

    public void delete(T entity) {
        this.repository.delete(entity);

    }

    public T findById(T entity) {
        return this.repository.findById((ID) entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Recurso %S %S não encontrado", entity.getClass().getSimpleName(), entity.getId())));

    }

    public Page<T> list(Pageable pageable) {
        return this.repository.findAll(pageable);
    }


}
