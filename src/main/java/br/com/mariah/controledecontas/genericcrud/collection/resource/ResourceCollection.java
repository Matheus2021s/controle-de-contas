package br.com.mariah.controledecontas.genericcrud.collection.resource;

import br.com.mariah.controledecontas.genericcrud.collection.Collection;
import br.com.mariah.controledecontas.genericcrud.resources.Resource;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class ResourceCollection extends Collection<Resource> {

    public ResourceCollection(final List<Resource> resources) {
        super(resources);
    }

    public ResourceItem resolveByEndpoint(String endpoint) {
        if (endpoint.startsWith("/")) {
            endpoint = endpoint.replaceFirst("/", "");
        }
        String[] endpointParts = endpoint.split("/");
        return getList().stream().map(resource -> getItemResource(endpointParts, resource))
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    private ResourceItem getItemResource(String[] endpoints, Resource resource) {

        ResourceItem resourceItem = findResourceByEndpoint(resource.getResources(), endpoints[0]);

        if (Objects.nonNull(resourceItem)) {
            for (int i = 1; i < endpoints.length; i++) {
                if (resourceItem.hasSubResources()
                        || (!resourceItem.hasSubResources() && i < endpoints.length)) {
                    resourceItem = findResourceByEndpoint(resourceItem.getSubItems(), endpoints[i]);
                }
            }
        }

        return resourceItem;

    }

    public ResourceItem findResourceByEndpoint(HashMap<String, ResourceItem> resources, String endpoint) {
        return resources.get(endpoint);
    }
}
