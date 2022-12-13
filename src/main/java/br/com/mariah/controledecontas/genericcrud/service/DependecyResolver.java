package br.com.mariah.controledecontas.genericcrud.service;

import br.com.mariah.controledecontas.genericcrud.anotation.CrudManaged;
import br.com.mariah.controledecontas.genericcrud.collection.persistence.PersistenceCollection;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class DependecyResolver {
    private final PersistenceCollection persistenceCollection;
    private final DTOResolver dtoResolver;

    public void resolve(GenericEntity entity, Class<? extends GenericEntity> entityClass) {

        if (Objects.nonNull(entity)) {

            for (Field declaredField : entityClass.getDeclaredFields()) {

                for (Annotation annotation : declaredField.getAnnotations()) {

                    if (annotation instanceof CrudManaged) {

                        GenericEntity o = getFieldValueFromEntity(entity, declaredField);
/*
                        String managegResourceName = ((CrudManaged) annotation).resource();

                        ResourceItem managedResourceItem = resourceResolver.resolve(managegResourceName);*/

                        System.out.println(declaredField.getType());

                        GenericEntity entityToSet = this.persistenceCollection.resolveByEntity(declaredField.getType())
                                .findById(o);

                        setFieldInObject(declaredField, entity, entityToSet);
                    }
                }
            }
        }
    }

    private static GenericEntity getFieldValueFromEntity(GenericEntity entity, Field declaredField) {
        declaredField.setAccessible(true);

        GenericEntity genericEntity = (GenericEntity) ReflectionUtils.getField(declaredField, entity);

        declaredField.setAccessible(false);

        return genericEntity;
    }

    private static void setFieldInObject(Field field, Object targetObject, Object value) {

        field.setAccessible(true);

        ReflectionUtils.setField(field, targetObject, value);

        field.setAccessible(false);
    }
}


