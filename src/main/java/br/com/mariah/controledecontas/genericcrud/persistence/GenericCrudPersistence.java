package br.com.mariah.controledecontas.genericcrud.persistence;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class GenericCrudPersistence<T extends GenericEntity, ID, R extends JpaRepository<T, ID>> implements GenericPersistence<T> {

    private final R repository;

    @Override
    public T save(T entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        this.repository.delete(entity);

    }

    @Override
    public T findById(T entity) {
        return this.repository.findById((ID) entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Recurso %S %S não encontrado", entity.getClass().getSimpleName(), entity.getId())));

    }

    @Override
    public Page<T> list(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
