package br.com.mariah.controledecontas.genericcrud.resources;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class ResourceItem {
    private final Class<? extends GenericEntity> entityClazz;
    private final Class<? extends GenericDTO> dtoClazz;
    private final Class<? extends GenericPersistence> persistenceClazz;
    private final HashMap<String, ResourceItem> subItems;

    public ResourceItem(Class<? extends GenericEntity> entityClazz, Class<? extends GenericDTO> dtoClazz, Class<? extends GenericPersistence> persistenceClazz, HashMap<String, ResourceItem> subItems) {
        this.entityClazz = entityClazz;
        this.dtoClazz = dtoClazz;
        this.persistenceClazz = persistenceClazz;
        this.subItems = subItems;
    }

    public ResourceItem(Class<? extends GenericEntity> entityClazz, Class<? extends GenericDTO> dtoClazz, Class<? extends GenericPersistence> persistenceClazz) {
        this.entityClazz = entityClazz;
        this.dtoClazz = dtoClazz;
        this.persistenceClazz = persistenceClazz;
        this.subItems = new HashMap<>();
    }


    public boolean hasSubResources() {
        return !this.subItems.isEmpty();
    }


}
