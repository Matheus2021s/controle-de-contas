package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Salario;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.SalarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class SalarioPersistence implements GenericPersistence<Salario> {

    private final SalarioRepository salarioRepository;

    @Override
    public Salario save(Salario entity) {
        entity.setDataEntrada(LocalDateTime.now());
        return this.salarioRepository.save(entity);
    }

    @Override
    public void delete(Salario entity) {
        this.salarioRepository.delete(entity);
    }

    @Override
    public Salario findById(Salario entity) {
        return this.salarioRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Salario %s não encontrado!", entity.getId())));
    }

    @Override
    public Page<Salario> list(Pageable pageable) {
        return this.salarioRepository.findAll(pageable);
    }


}
