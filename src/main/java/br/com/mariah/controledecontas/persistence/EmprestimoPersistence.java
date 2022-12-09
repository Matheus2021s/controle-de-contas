package br.com.mariah.controledecontas.persistence;

import br.com.mariah.controledecontas.domain.Emprestimo;
import br.com.mariah.controledecontas.genericcrud.exception.ResourceNotFoundException;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericPersistence;
import br.com.mariah.controledecontas.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmprestimoPersistence implements GenericPersistence<Emprestimo> {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public Emprestimo save(Emprestimo entity) {
        return emprestimoRepository.save(entity);
    }

    @Override
    public void delete(Emprestimo entity) {
        emprestimoRepository.delete(entity);
    }

    @Override
    public Emprestimo findById(Emprestimo entity) {
        return emprestimoRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Emprestimo %s não encontrado!", entity.getId())));

    }

    @Override
    public Page<Emprestimo> list(Pageable pageable) {
        return emprestimoRepository.findAll(pageable);
    }
}
