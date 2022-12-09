package br.com.mariah.controledecontas.dto;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import static br.com.mariah.controledecontas.genericcrud.dto.DTOType.ALL;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Setter
@Getter
public class CartaoDTO implements GenericDTO<Cartao,Long> {

    @DTO(types = ALL)
    private Long id;

    @DTO(types = ALL)
    private Double limite;

    @DTO(types = ALL)
    private BancoDTO banco = new BancoDTO();

    @JsonIgnore
    @Override
    public Cartao toEntity() {
        return Cartao.builder()
                .id(getId())
                .limite(getLimite())
                .banco(getBanco().toEntity())
                .build();
    }
}
