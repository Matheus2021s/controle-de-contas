package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Orcamento;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.OrcamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrcamentoPersistence implements GenericPersistence<Orcamento> {

    private final OrcamentoRepository orcamentoRepository;

    @Override
    public Orcamento save(Orcamento entity) {
        return this.orcamentoRepository.save(entity);
    }

    @Override
    public void delete(Orcamento entity) {
        this.orcamentoRepository.delete(entity);
    }

    @Override
    public Orcamento findById(Orcamento entity) {
        return this.orcamentoRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Orcamento %s não encontrado!", entity.getId())));
    }

    @Override
    public Page<Orcamento> list(Pageable pageable) {
        return this.orcamentoRepository.findAll(pageable);
    }

}
