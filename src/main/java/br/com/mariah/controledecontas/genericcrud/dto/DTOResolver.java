package br.com.mariah.controledecontas.genericcrud.dto;

import br.com.mariah.controledecontas.genericcrud.anotation.EntityRestDeserializerModule;
import br.com.mariah.controledecontas.genericcrud.collection.dto.DTOCollection;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.mariah.controledecontas.genericcrud.dto.DTOType.*;

@Component
public class DTOResolver<D extends GenericDTO, ID, E extends GenericEntity<ID>> {

    private final DTOCollection dtoCollection;
    private Class<D> dtoClazz;

    public DTOResolver(DTOCollection dtoCollection) {
        this.dtoCollection = dtoCollection;
    }

    public D create(Class<D> dtoClazz, String createDTO) {
        this.dtoClazz = dtoClazz;

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

    public D response(Class<D> dtoClazz, E genericEntity) {
        this.dtoClazz = dtoClazz;

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

    public D update(Class<D> dtoClazz, String updateDTO) {
        this.dtoClazz = dtoClazz;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(defaultModules());

        objectMapper.registerModule(new EntityRestDeserializerModule(dtoClazz, UPDATE, defaultModules()));

        try {
            return objectMapper.readValue(updateDTO, this.dtoClazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public D idDto(Class<D> dtoClazz, Long id) {
        this.dtoClazz = dtoClazz;

        D t = BeanUtils.instantiateClass(dtoClazz);
        t.setId(id);

        return t;
    }

    public Page<D> pageResponse(Class<D> dtoClazz, Page<E> genericEntityPage) {
        return genericEntityPage.map(entity -> response(dtoClazz, entity));
    }
}
