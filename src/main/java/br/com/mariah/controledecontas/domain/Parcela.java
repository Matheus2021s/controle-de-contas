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
public class Parcela implements GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double valor;

    private LocalDateTime vencimento;

    @CrudManaged(resource = "emprestimo")
    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;


    @CrudManaged(resource = "fatura")
    @OneToOne
    @JoinColumn(name = "fatura_id")
    private Fatura fatura;

}
