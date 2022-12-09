package br.com.mariah.controledecontas.genericcrud.anotation;

import br.com.mariah.controledecontas.genericcrud.dto.DTOType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class EntityRestDeserializerModule<T> extends SimpleModule {

    public EntityRestDeserializerModule(Class<T> deserializationClass, DTOType dtoType, List<SimpleModule> modules) {
        addDeserializer(deserializationClass, new StdDeserializer<>(deserializationClass) {


            @Override
            public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                modules.forEach(simpleModule -> objectMapper.registerModule(simpleModule));

                String s = objectMapper.writeValueAsString(assembleJsonResultField(deserializationClass, jsonParser, dtoType, modules));

                return objectMapper.readValue(s, deserializationClass);
            }
        });
    }

    private Map<String, Object> assembleJsonResultField(Class<T> deserializationClass, JsonParser jsonParser, DTOType dtoType, List<SimpleModule> modules) throws IOException {
        Map<String, Object> result = new HashMap<>();
        TreeNode treeNode = jsonParser.readValueAsTree();
        for (Iterator<String> it = treeNode.fieldNames(); it.hasNext(); ) {
            String nodeName = it.next();

            Optional<Field> fieldFound = getFieldByNameOrJsonPropertyValue(deserializationClass.getDeclaredFields(), nodeName);
            if (fieldFound.isPresent()) {
                Field field = fieldFound.get();
                if (fieldContainsDTOTypeAnotation(dtoType, field)) {
                    result.put(field.getName(), getFieldValueFromNodeTree(treeNode, nodeName, field, jsonParser, dtoType, modules));
                }
            }
        }
        return result;
    }

    private static Object getFieldValueFromNodeTree(TreeNode treeNode, String nodeName, Field field, JsonParser jsonParser, DTOType dtoType, List<SimpleModule> modules) throws IOException {

        Optional<Annotation> dtoReferenceAnotation = getDTOReferenceAnotation(field);

        if (dtoReferenceAnotation.isPresent()) {

            ObjectMapper objectMapper = new ObjectMapper();

            JsonParser traverse =  treeNode.get(nodeName).traverse();

            traverse.setCodec(jsonParser.getCodec());

            Annotation annotation = dtoReferenceAnotation.get();

            objectMapper.registerModule(new EntityRestDeserializerModule(field.getType(), dtoType, modules));

            return objectMapper.readValue(traverse, ((DTO) annotation).reference());

        }

        return treeNode.get(nodeName);
    }

    private static Optional<Annotation> getDTOReferenceAnotation(Field field) {
        return Arrays.stream(field.getAnnotations())
                .filter(annotation -> annotation instanceof DTO
                        && !((DTO) annotation).reference().equals(Object.class)).findFirst();
    }

    private static boolean fieldContainsDTOTypeAnotation(DTOType dtoType, Field field) {
        return Arrays.stream(field.getAnnotations())
                .anyMatch(annotation -> annotation instanceof DTO
                        && Arrays.stream(((DTO) annotation).types())
                        .anyMatch(dtoType1 -> dtoType1.equals(dtoType)
                                || dtoType1.equals(DTOType.ALL)));
    }


    private Optional<Field> getFieldByNameOrJsonPropertyValue(Field[] fields, String nameToFind) {
        return Arrays.stream(fields)
                .filter(field -> fieldContainsJsonColumnValue(nameToFind, field)
                        || fieldContainsName(nameToFind, field))
                .findFirst();
    }


    private Optional<Method> getMethodByNameOrJsonPropertyValue(Method[] methods, String nameToFind) {
        return Arrays.stream(methods)
                .filter(method -> methodContainsJsonColumnValue(nameToFind, method)
                        || methodContainsName(nameToFind, method))
                .findFirst();
    }


    private static boolean methodContainsName(String nameToFind, Method method) {
        return method.getName().equalsIgnoreCase(String.format("GET%S", nameToFind.replace("-", "")));
    }

    private static boolean methodContainsJsonColumnValue(String nameToFind, Method method) {
        return Arrays.stream(method.getAnnotations())
                .anyMatch(annotation -> annotation instanceof JsonProperty
                        && ((JsonProperty) annotation).value()
                        .equalsIgnoreCase(nameToFind.replace("-", "")));
    }


    private static boolean fieldContainsName(String nameToFind, Field field) {
        return field.getName().equalsIgnoreCase(nameToFind.replace("-", ""));
    }

    private static boolean fieldContainsJsonColumnValue(String nameToFind, Field field) {
        return Arrays.stream(field.getAnnotations())
                .anyMatch(annotation -> annotation instanceof JsonProperty
                        && ((JsonProperty) annotation).value()
                        .equalsIgnoreCase(nameToFind.replace("-", "")));
    }

}
