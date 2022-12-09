package br.com.mariah.controledecontas.domain;

import br.com.mariah.controledecontas.genericcrud.anotation.CrudManaged;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Fatura implements GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime vencimento;

    @CrudManaged(resource = "banco/cartao")
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

}
