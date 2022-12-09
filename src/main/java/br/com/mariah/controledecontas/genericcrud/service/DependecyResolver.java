package br.com.mariah.controledecontas.genericcrud.service;

import br.com.mariah.controledecontas.genericcrud.anotation.CrudManaged;
import br.com.mariah.controledecontas.genericcrud.collection.persistence.PersistenceCollection;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceResolver;
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
    private final ResourceResolver resourceResolver;
    private final DTOResolver dtoResolver;

    public void resolve(GenericEntity entity, ResourceItem resource) {

        if (Objects.nonNull(entity)) {

            for (Field declaredField : resource.getEntityClazz().getDeclaredFields()) {

                for (Annotation annotation : declaredField.getAnnotations()) {

                    if (annotation instanceof CrudManaged) {

                        GenericEntity o = getFieldValueFromEntity(entity, declaredField);

                        String managegResourceName = ((CrudManaged) annotation).resource();

                        ResourceItem managedResourceItem = resourceResolver.resolve(managegResourceName);

                        GenericEntity entityToSet = this.persistenceCollection.resolveByClass(managedResourceItem.getPersistenceClazz())
                                .findById(dtoResolver.idDto(managedResourceItem, (Long) o.getId()).toEntity());

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


