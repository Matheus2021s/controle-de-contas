package br.com.mariah.controledecontas.genericcrud.service;


import br.com.mariah.controledecontas.genericcrud.collection.persistence.PersistenceCollection;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class GenericCrudService {

    private final PersistenceCollection persistenceCollection;

    private final DependecyResolver dependecyResolver;

    public GenericEntity create(ResourceItem resource, GenericEntity entity) {
        log.info("Service create resource : {}", resource);

        dependecyResolver.resolve(entity, resource);

        return persistenceCollection.resolveByClass(resource.getPersistenceClazz()).save(entity);
    }


    public GenericEntity update(ResourceItem resource, GenericEntity entity) {
        log.info("Service create resource : {}", resource);

        dependecyResolver.resolve(entity, resource);

        GenericPersistence genericPersistence = persistenceCollection.resolveByClass(resource.getPersistenceClazz());

        genericPersistence.findById(entity);

        return genericPersistence.save(entity);
    }

    public GenericEntity findById(ResourceItem resource, GenericEntity entity) {
        log.info("Find by id {} resource : {}", entity.getId(), resource);

        return persistenceCollection.resolveByClass(resource.getPersistenceClazz()).findById(entity);
    }


    public void delete(ResourceItem resource, GenericEntity entity) {
        log.info("Delete by id {} resource : {}", entity.getId(), resource);

        GenericPersistence genericPersistence = persistenceCollection.resolveByClass(resource.getPersistenceClazz());

        GenericEntity genericEntity = genericPersistence.findById(entity);

        genericPersistence.delete(genericEntity);
    }


    public Page<GenericEntity> paginatedList(ResourceItem resource, Pageable pageable) {
        log.info("Paginated list: {}  resource : {}", pageable, resource);

        return persistenceCollection.resolveByClass(resource.getPersistenceClazz()).list(pageable);
    }
}
