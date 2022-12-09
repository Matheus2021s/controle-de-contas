package br.com.mariah.controledecontas.genericcrud.resources;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;


@Setter
@Getter
public abstract class Resource implements CollectionTarget {
    private HashMap<String, ResourceItem> resources;

}
