package br.com.mariah.controledecontas.domain;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Orcamento implements GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;


}
