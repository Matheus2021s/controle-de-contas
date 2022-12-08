package br.com.mariah.controledecontas.dto;

import br.com.mariah.controledecontas.domain.Orcamento;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import static br.com.mariah.controledecontas.genericcrud.anotation.DTOType.ALL;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Setter
@Getter
public class OrcamentoDTO implements GenericDTO<Orcamento, Long> {

    @DTO(types = ALL)
    private Long id;

    @DTO(types = ALL)
    private String name;

    @JsonIgnore
    @Override
    public Orcamento toEntity() {
        return Orcamento.builder()
                .id(getId())
                .name(getName())
                .build();
    }


}
