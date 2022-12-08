package br.com.mariah.controledecontas;

import br.com.mariah.controledecontas.domain.*;
import br.com.mariah.controledecontas.genericcrud.collection.persistence.PersistenceCollection;
import br.com.mariah.controledecontas.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@SpringBootApplication
public class ControleDeContasApplication implements CommandLineRunner {

	private final PersistenceCollection persistenceCollection;

	public static void main(String[] args) {
		SpringApplication.run(ControleDeContasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Banco bancoDoBrasil = Banco.builder().name("banco do brasil").build();

		Cartao cartaoBB = Cartao.builder().banco(bancoDoBrasil).limite(100000D).build();

		Emprestimo emprestimo = Emprestimo.builder().valor(5000D).banco(bancoDoBrasil).dataContratacao(LocalDateTime.now()).build();

		Orcamento orcamentoMesDeMaio = Orcamento.builder().name("orcamento mes de maio").build();

		Salario salario = Salario.builder().orcamento(orcamentoMesDeMaio).banco(bancoDoBrasil).dataEntrada(LocalDateTime.now()).valor(100000D).build();


		persistenceCollection.resolveByClass(BancoPersistence.class).save(bancoDoBrasil);

		persistenceCollection.resolveByClass(CartaoPersistence.class).save(cartaoBB);

		persistenceCollection.resolveByClass(EmprestimoPersistence.class).save(emprestimo);

		persistenceCollection.resolveByClass(OrcamentoPersistence.class).save(orcamentoMesDeMaio);

		persistenceCollection.resolveByClass(SalarioPersistence.class).save(salario);

	}
}
