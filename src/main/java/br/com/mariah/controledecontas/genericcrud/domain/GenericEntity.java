package br.com.mariah.controledecontas.genericcrud.domain;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;

public interface GenericEntity<ID> extends CollectionTarget {
    ID getId();

    void setId(ID id);


}
