package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartaoPersistence implements GenericPersistence<Cartao> {

    private final CartaoRepository cartaoRepository;

    @Override
    public Cartao save(Cartao entity) {
        return this.cartaoRepository.save(entity);
    }

    @Override
    public void delete(Cartao entity) {
        this.cartaoRepository.delete(entity);
    }

    @Override
    public Cartao findById(Cartao entity) {
        return this.cartaoRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cartao %s não encontrado!", entity.getId())));

    }

    @Override
    public Page<Cartao> list(Pageable pageable) {
        return this.cartaoRepository.findAll(pageable);
    }
}
