package br.com.mariah.controledecontas.genericcrud.domain;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;

public interface GenericEntity<T> extends CollectionTarget {
   T getId();


}
