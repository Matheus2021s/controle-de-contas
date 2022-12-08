package br.com.mariah.controledecontas.domain;

import br.com.mariah.controledecontas.genericcrud.anotation.CrudManaged;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Cartao implements GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double limite;

    @CrudManaged(resource = "banco")
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;
}
