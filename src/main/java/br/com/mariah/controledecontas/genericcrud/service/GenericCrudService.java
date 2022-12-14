package br.com.mariah.controledecontas.genericcrud.service;


import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
@Log4j2
public abstract class GenericCrudService<E extends GenericEntity, ID, T extends JpaRepository<E, ID>, P extends GenericCrudPersistence<E, ID, T>> {
    private final P persistence;
    private DependecyResolver dependecyResolver;
    private final Class<E> genericEntity;

    protected GenericCrudService(P persistence, Class<E> genericEntity) {
        this.persistence = persistence;
        this.genericEntity = genericEntity;
    }


    public E create(E entity) {
        log.info("Service create resource : {}", genericEntity.getSimpleName());

        dependecyResolver.resolve(entity, entity.getClass());

        return persistence.save(entity);
    }


    public E update(E entity) {
        log.info("Service create resource : {}", genericEntity.getSimpleName());

        dependecyResolver.resolve(entity, entity.getClass());

        persistence.findById(entity);

        return persistence.save(entity);
    }

    public E findById(E entity) {
        log.info("Find by id {} resource : {}", entity.getId(), genericEntity.getSimpleName());

        return persistence.findById(entity);
    }


    public void delete(E entity) {
        log.info("Delete by id {} resource : {}", entity.getId(), genericEntity.getSimpleName());

        E genericEntity = persistence.findById(entity);

        persistence.delete(genericEntity);
    }


    public Page<E> paginatedList(Pageable pageable) {
        log.info("Paginated list: {}  resource : {}", pageable, genericEntity.getSimpleName());

        return persistence.list(pageable);
    }

    @Autowired
    public void setDependecyResolver(DependecyResolver dependecyResolver) {
        this.dependecyResolver = dependecyResolver;
    }

}
