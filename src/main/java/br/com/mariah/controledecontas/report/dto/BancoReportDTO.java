package br.com.mariah.controledecontas.report.dto;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BancoReportDTO implements GenericReportDTO<Banco> {

    private Long id;

    private String name;

    @Override
    public GenericReportDTO from(Banco entity) {
        return BancoReportDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
