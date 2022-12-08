package br.com.mariah.controledecontas.genericcrud.resources;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import lombok.Getter;

@Getter
public class ResourceItem {
    private final Class<? extends GenericEntity> entityClazz;
    private final Class<? extends GenericDTO> dtoClazz;
    private final Class<? extends GenericPersistence> persistenceClazz;

    public ResourceItem(Class<? extends GenericEntity> entityClazz, Class<? extends GenericDTO> dtoClazz, Class<? extends GenericPersistence> persistenceClazz) {
        this.entityClazz = entityClazz;
        this.dtoClazz = dtoClazz;
        this.persistenceClazz = persistenceClazz;
    }
}
