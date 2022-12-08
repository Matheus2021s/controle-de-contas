package br.com.mariah.controledecontas.genericcrud.collection.resource;

import br.com.mariah.controledecontas.genericcrud.collection.Collection;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.resources.Resource;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceCollection extends Collection<Resource> {

    public ResourceCollection(final List<Resource> resources) {
        super(resources);
    }

    public ResourceItem resolveByEndpoint(String endpoint) {
        return getList().stream().map(resource -> resource.resolve(endpoint))
                .findFirst().orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Resource \"%S\" not found!", endpoint)));

    }
}
