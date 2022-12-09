package br.com.mariah.controledecontas.genericcrud.resources;

import br.com.mariah.controledecontas.genericcrud.collection.resource.ResourceCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ResourceResolver {

    private final ResourceCollection resourceCollection;

    public ResourceItem resolve(String endpointRest) {
        if (Objects.nonNull(endpointRest) && !endpointRest.isEmpty()) {
            return resourceCollection.resolveByEndpoint(endpointRest);
        }
        return null;
    }


}
