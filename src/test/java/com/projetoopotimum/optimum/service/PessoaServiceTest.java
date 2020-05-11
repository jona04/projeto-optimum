package com.projetoopotimum.optimum.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projetoopotimum.optimum.exception.RegraDeNegocioException;
import com.projetoopotimum.optimum.model.entity.Contato;
import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.enums.TipoContato;
import com.projetoopotimum.optimum.service.impl.PessoaServiceImpl;

@ExtendWith( SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PessoaServiceTest {
	
	@SpyBean
	public PessoaServiceImpl pessoaService;
	
//	@Autowired
//	TestEntityManager entityManager;
	
//	@Test
//	public void deveSalvarPessoa() {
//		
//		//cenario
//		Pessoa pessoa = criarPessoaComId();
//		Contato contato = criarContatoComId(pessoa);
//
//		//acao
//		Pessoa pessoaSalva = entityManager.persist(pessoa);
//		Contato contatoSalvo = entityManager.persist(contato);
//		
//		//verificacao
//		Assertions.assertTrue(pessoaSalva.getNome().equals("Jonatas"));
//		Assertions.assertTrue(pessoaSalva.getCpf().equals("04454360340"));
//		Assertions.assertTrue(contatoSalvo.getValor().equals("jonatas.iw@gmail.com"));
//		
//		
//	}
	
	@Test
	public void naoDeveSalvarPessoaQuandoCpfInvalido() {
		
		//cenario
		Pessoa pessoa = Pessoa.builder().cpf("0445436034").build();
		Contato contato = criarContatoComId(pessoa);
		
		//acao
		Exception exception = Assertions.assertThrows(RegraDeNegocioException.class, ()-> pessoaService.salvarPessoa(pessoa));
		
		//verificacao
		String expectedMessage = "Favor informar um CPF válido.";
	    String actualMessage = exception.getMessage();
	 
	    Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void naoDeveSalvarPessoaQuandoNaoPossuirContato() {
		
		//cenario
		Pessoa pessoa = Pessoa.builder().cpf("04454360340").nascimento(LocalDate.of(2000,1,17)).build();
		Contato contato = Contato.builder().valor("").build();
		
		//acao
		Exception exception = Assertions.assertThrows(RegraDeNegocioException.class, ()-> pessoaService.salvarPessoa(pessoa));
		
		//verificacao
		String expectedMessage = "Você deve informar pelo menos 1 contato.";
	    String actualMessage = exception.getMessage();
	 
	    Assertions.assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	public void naoDeveSalvarPessoaQuandoNascimentoInvalido() {
		
		//cenario
		Pessoa pessoa = Pessoa.builder().cpf("04454360340").nascimento(LocalDate.of(2005,1,17)).build();
		Contato contato = criarContatoComId(pessoa);
		
		//acao
		Exception exception = Assertions.assertThrows(RegraDeNegocioException.class, ()-> pessoaService.salvarPessoa(pessoa));
		
		//verificacao
		String expectedMessage = "Cadastro apenas para maiores de 18 anos.";
	    String actualMessage = exception.getMessage();
	 
	    Assertions.assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	
	
	public Pessoa criarPessoaComId() {
		Pessoa pessoa = Pessoa.builder()
				.nome("Jonatas")
				.cpf("04454360340")
				.nascimento(LocalDate.of(1993,1,17))
				.endereco("Rua doutor area lao")
				.bairro("centro")
				.cidade("Teresina")
				.estado("Piaui")
				.cep("64000310")
				.build();
				
		return pessoa;
	}
	
	public Contato criarContatoComId(Pessoa pessoa) {
		Contato contato = Contato.builder()
				.tipo(TipoContato.EMAIL)
				.valor("jonatas.iw@gmail.com")
				.build();
		
		return contato;
	}
	
}
