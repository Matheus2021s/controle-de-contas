package br.com.mariah.controledecontas.report.dto;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CartaoReportDTO implements GenericReportDTO<Cartao> {

    private Long id;

    private Double limite;

    private String banco;

    @Override
    public GenericReportDTO from(Cartao entity) {
        return CartaoReportDTO.builder()
                .id(entity.getId())
                .limite(entity.getLimite())
                .banco(String.format("%s - %s", entity.getBanco().getId(), entity.getBanco().getName()))
                .build();
    }
}
