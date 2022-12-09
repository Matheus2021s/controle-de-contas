package br.com.mariah.controledecontas.genericcrud.collection.dto;

import br.com.mariah.controledecontas.genericcrud.collection.Collection;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DTOCollection extends Collection<GenericDTO> {

    protected DTOCollection(final List<GenericDTO> genericDTOList) {
        super(genericDTOList);
    }


}
