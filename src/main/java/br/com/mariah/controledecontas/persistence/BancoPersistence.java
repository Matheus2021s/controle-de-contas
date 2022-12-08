package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.BancoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BancoPersistence implements GenericPersistence<Banco> {

    private final BancoRepository bancoRepository;

    @Override
    public Banco save(Banco entity) {
        return bancoRepository.save(entity);
    }

    @Override
    public void delete(Banco entity) {
        this.bancoRepository.delete(entity);
    }

    @Override
    public Banco findById(Banco entity) {
        return this.bancoRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Banco %s não encontrado!", entity.getId())));
    }

    @Override
    public Page<Banco> list(Pageable pageable) {
        return this.bancoRepository.findAll(pageable);
    }


}
