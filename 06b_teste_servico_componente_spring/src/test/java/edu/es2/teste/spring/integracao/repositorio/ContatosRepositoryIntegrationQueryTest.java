package edu.es2.teste.spring.integracao.repositorio;

import edu.es2.teste.spring.integracao.modelo.Contato;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;


@DataJpaTest
public class ContatosRepositoryIntegrationQueryTest {

	@Autowired
	private ContatoRepository contatoRepository;

	@BeforeEach
	public void before() {
		Contato contato = new Contato("Chefe", "98", "123456789");
		contatoRepository.save(contato );
		contato = new Contato("Novo Chefe", "98", "222123456");
		contatoRepository.save(contato );
		contato = new Contato("Chefe Mais Antigo", "98", "987654321");
		contatoRepository.save(contato );
		contato = new Contato("Amigo", "98", "222333444");
		contatoRepository.save(contato );
	}

	@AfterEach
	public void after() {
		contatoRepository.deleteAll();
	}


	@Test
	public void deveBuscarContatoPeloNome() {
		Contato contato= contatoRepository.findFirstByNome("Amigo");
		Assertions.assertTrue(contato.getNome().equals("Amigo"));
	}

	@Test
	public void deveRetornarTodosOsContatosOrdenadosEmOrdemCrescente()  {


		List<Contato> contatos;
		contatos = contatoRepository
				.todos( Sort.by("nome").ascending() );

		Assertions.assertTrue(contatos.get(0).getNome().equals("Amigo"));
		Assertions.assertTrue(contatos.get(1).getNome().equals("Chefe"));
		Assertions.assertTrue(contatos.get(2).getNome().equals("Chefe Mais Antigo"));
		Assertions.assertTrue(contatos.get(3).getNome().equals("Novo Chefe"));


	}


}