package br.com.mariah.controledecontas.genericcrud.dto;

import br.com.mariah.controledecontas.genericcrud.anotation.EntityRestDeserializerModule;
import br.com.mariah.controledecontas.genericcrud.collection.dto.DTOCollection;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.mariah.controledecontas.genericcrud.dto.DTOType.*;

@Component
public class DTOResolver<T extends GenericDTO> {

    private final DTOCollection dtoCollection;
    private Class<T> dtoClazz;

    public DTOResolver(DTOCollection dtoCollection) {
        this.dtoCollection = dtoCollection;
    }

    public GenericDTO create(ResourceItem resource, String createDTO) {
        this.dtoClazz = (Class<T>) resource.getDtoClazz();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(defaultModules());

        objectMapper.registerModule(new EntityRestDeserializerModule(dtoClazz, CREATE, defaultModules()));

        try {
            return objectMapper.readValue(createDTO, this.dtoClazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<JavaTimeModule> defaultModules() {
        return List.of(new JavaTimeModule());
    }

    public GenericDTO response(ResourceItem resource, GenericEntity genericEntity) {
        this.dtoClazz = (Class<T>) resource.getDtoClazz();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(defaultModules());

        objectMapper.registerModule(new EntityRestDeserializerModule(dtoClazz, RESPONSE, defaultModules()));

        try {
            String s = objectMapper.writeValueAsString(genericEntity);
            return objectMapper.readValue(s, this.dtoClazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public GenericDTO update(ResourceItem resource, String updateDTO) {
        this.dtoClazz = (Class<T>) resource.getDtoClazz();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(defaultModules());

        objectMapper.registerModule(new EntityRestDeserializerModule(dtoClazz, UPDATE, defaultModules()));

        try {
            return objectMapper.readValue(updateDTO, this.dtoClazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public GenericDTO idDto(ResourceItem resource, Long id) {
        this.dtoClazz = (Class<T>) resource.getDtoClazz();

        T t = BeanUtils.instantiateClass(dtoClazz);
        t.setId(id);

        return t;
    }

    public Page<GenericDTO> pageResponse(ResourceItem resolvedResource, Page<GenericEntity> genericEntityPage) {
        return genericEntityPage.map(entity -> response(resolvedResource, entity));
    }
}
