package br.com.mariah.controledecontas.dto;

import br.com.mariah.controledecontas.domain.Emprestimo;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.anotation.DTOType;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Setter
@Getter
public class EmprestimoDTO implements GenericDTO<Emprestimo,Long> {

    @DTO(types = DTOType.ALL)
    private Long id;

    @DTO(types = DTOType.ALL)
    private Double valor;

    @DTO(types = DTOType.ALL)
    private LocalDateTime dataContratacao;

    @DTO(types = DTOType.ALL)
    private BancoDTO banco = new BancoDTO();

    @Override
    public Emprestimo toEntity() {
        return Emprestimo.builder()
                .id(getId())
                .valor(getValor())
                .dataContratacao(getDataContratacao())
                .banco(getBanco().toEntity())
                .build();
    }
}
