package br.com.mariah.controledecontas.dto;


import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import static br.com.mariah.controledecontas.genericcrud.dto.DTOType.ALL;

@Setter
@Getter
public class BancoDTO implements GenericDTO<Banco, Long> {

    @DTO(types = ALL)
    private Long id;

    @DTO(types = ALL)
    private String name;


    @JsonIgnore
    @Override
    public Banco toEntity() {
        return Banco.builder()
                .id(getId())
                .name(getName())
                .build();

    }

}
