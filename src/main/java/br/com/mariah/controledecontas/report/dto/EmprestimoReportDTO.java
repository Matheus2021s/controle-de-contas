package br.com.mariah.controledecontas.report.dto;

import br.com.mariah.controledecontas.domain.Emprestimo;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmprestimoReportDTO implements GenericReportDTO<Emprestimo> {

    private Long id;

    private Double valor;

    private LocalDateTime dataContratacao;

    private String banco;

    @Override
    public GenericReportDTO from(Emprestimo entity) {
        return EmprestimoReportDTO.builder()
                .id(entity.getId())
                .valor(entity.getValor())
                .dataContratacao(entity.getDataContratacao())
                .banco(entity.getBanco().getName())
                .build();
    }
}
