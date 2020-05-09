package com.projetoopotimum.optimum.model.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projetoopotimum.optimum.model.entity.Contato;
import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.enums.TipoContato;

@SpringBootTest
@ExtendWith( SpringExtension.class)
public class PessoaRepositoryTest {

	
	@Autowired
	public PessoaRepository pessoaRepository;
	
	@Autowired
	public ContatoRepository contatoRepository;
	
	
	@Test
	public void devePersistirUmaPessoaNaBaseDeDadosComUmContato() {
		
		//cenario
		Pessoa pessoa = criarPessoa();
		Contato contato = criarContato(pessoa);
		
		//acao
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		Contato contatoSalvo = contatoRepository.save(contato);
		
		//verificacao
		Assertions.assertTrue(pessoaSalva.getId() != null);
	}
	
	
	
	
	public Pessoa criarPessoa() {
		Pessoa pessoa = Pessoa.builder()
				.nome("Jonatas")
				.cpf("04454360340")
				.nascimento(LocalDate.of(1993,1,17))
				.endereço("Rua doutor area lao")
				.bairro("centro")
				.cidade("Teresina")
				.estado("Piaui")
				.cep("64000310")
				.build();
				
		return pessoa;
	}
	
	public Contato criarContato(Pessoa pessoa) {
		Contato contato = Contato.builder()
				.tipo(TipoContato.EMAIL)
				.valor("jonatas.iw@gmail.com")
				.pessoa(pessoa)
				.build();
		
		return contato;
	}
	
}
