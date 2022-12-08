package br.com.mariah.controledecontas.dto;

import br.com.mariah.controledecontas.domain.Salario;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.com.mariah.controledecontas.genericcrud.anotation.DTOType.ALL;


@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Setter
@Getter
public class SalarioDTO implements GenericDTO<Salario, Long> {

    @DTO(types = ALL)
    private Long id;

    @DTO(types = ALL)
    private LocalDateTime dataEntrada;

    @DTO(types = ALL)
    private Double valor;

    @DTO(types = ALL, reference = OrcamentoDTO.class)
    private OrcamentoDTO orcamento = new OrcamentoDTO();

    @DTO(types = ALL, reference = BancoDTO.class)
    private BancoDTO banco = new BancoDTO();


    @Override
    public Salario toEntity() {
        return Salario.builder()
                .id(getId())
                .valor(getValor())
                .dataEntrada(getDataEntrada())
                .orcamento(getOrcamento().toEntity())
                .banco(getBanco().toEntity())
                .build();
    }

}
