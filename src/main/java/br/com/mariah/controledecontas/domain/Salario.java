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
public class Salario implements GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime dataEntrada;

    private Double valor;

    @CrudManaged(resource="orcamento")
    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;

    @CrudManaged(resource="banco")
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

}