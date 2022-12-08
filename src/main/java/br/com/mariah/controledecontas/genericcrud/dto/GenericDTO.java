package br.com.mariah.controledecontas.genericcrud.dto;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;

public interface GenericDTO<T extends GenericEntity, K> extends CollectionTarget {

    void setId(K id);

    T toEntity();

}
