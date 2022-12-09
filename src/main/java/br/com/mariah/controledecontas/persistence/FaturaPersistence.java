package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Fatura;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.FaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FaturaPersistence implements GenericPersistence<Fatura> {

    private final FaturaRepository faturaRepository;
    @Override
    public Fatura save(Fatura entity) {
        return faturaRepository.save(entity);
    }

    @Override
    public void delete(Fatura entity) {
        faturaRepository.delete(entity);
    }

    @Override
    public Fatura findById(Fatura entity) {
        return faturaRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Fatura %s não encontrado!", entity.getId())));

    }

    @Override
    public Page<Fatura> list(Pageable pageable) {
        return faturaRepository.findAll(pageable);
    }
}
