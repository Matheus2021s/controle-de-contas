package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Parcela;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ParcelaPersistence implements GenericPersistence<Parcela> {
    private final ParcelaRepository parcelaRepository;

    @Override
    public Parcela save(Parcela entity) {
        return parcelaRepository.save(entity);
    }

    @Override
    public void delete(Parcela entity) {
        parcelaRepository.delete(entity);
    }

    @Override
    public Parcela findById(Parcela entity) {
        return parcelaRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Parcela %s não encontrado!", entity.getId())));

    }

    @Override
    public Page<Parcela> list(Pageable pageable) {
        return parcelaRepository.findAll(pageable);
    }
}
