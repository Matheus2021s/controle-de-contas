package br.com.mariah.controledecontas.dto;

import br.com.mariah.controledecontas.domain.Fatura;
import br.com.mariah.controledecontas.genericcrud.anotation.DTO;
import br.com.mariah.controledecontas.genericcrud.dto.DTOType;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.com.mariah.controledecontas.genericcrud.dto.DTOType.*;


@Setter
@Getter
public class FaturaDTO implements GenericDTO<Fatura,Long> {

    @DTO(types = ALL)
    private Long id;

    @DTO(types = ALL)
    private LocalDateTime vencimento;

    @DTO(types = ALL)
    private CartaoDTO cartao = new CartaoDTO();

    @Override
    public Fatura toEntity() {
        return Fatura.builder()
                .id(getId())
                .vencimento(getVencimento())
                .cartao(getCartao().toEntity())
                .build();
    }
}
