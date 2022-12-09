package br.com.mariah.controledecontas.dto;

import br.com.mariah.controledecontas.domain.Parcela;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.dto.DTOType;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.com.mariah.controledecontas.genericcrud.dto.DTOType.*;

@Setter
@Getter
public class ParcelaDTO implements GenericDTO<Parcela,Long> {

    @DTO(types = ALL)
    private Long id;

    @DTO(types = ALL)
    private Double valor;

    @DTO(types = ALL)
    private LocalDateTime vencimento;

    @DTO(types = ALL)
    private EmprestimoDTO emprestimo = new EmprestimoDTO();

    @DTO(types = ALL)
    private FaturaDTO fatura = new FaturaDTO();

    @JsonIgnore
    @Override
    public Parcela toEntity() {
        return Parcela.builder()
                .id(getId())
                .valor(getValor())
                .vencimento(getVencimento())
                .emprestimo(getEmprestimo().toEntity())
                .fatura(getFatura().toEntity())
                .build();
    }
}
