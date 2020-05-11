package com.projetoopotimum.optimum.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoopotimum.optimum.exception.RegraDeNegocioException;
import com.projetoopotimum.optimum.model.entity.Contato;
import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.enums.TipoContato;
import com.projetoopotimum.optimum.model.repository.ContatoRepository;
import com.projetoopotimum.optimum.model.repository.PessoaRepository;
import com.projetoopotimum.optimum.service.ContatoService;
import com.projetoopotimum.optimum.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService, ContatoService {

	@Resource
	PessoaRepository pessoaRepository;
	
	@Resource
	ContatoRepository contatoRepository;
	
	public PessoaServiceImpl(PessoaRepository pessoaRepository, ContatoRepository contatoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.contatoRepository = contatoRepository;
	}
	
	
	@Override
	@Transactional
	public Pessoa salvarPessoa(Pessoa pessoa) {
		validarCPF(pessoa);
		validarNascimento(pessoa);
		validarQuantidadeContato(pessoa);
		validarEmail(pessoa.getContatos());
		return pessoaRepository.save(pessoa);
	}
	
	@Override
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}
	
	@Override
	public void validarCPF(Pessoa pessoa){
		try {
			if ( pessoa.getCpf().isEmpty() || pessoa.getCpf().length() < 11) {
				throw new RegraDeNegocioException("Favor informar um CPF válido.");
			}
		} catch (Exception e) {
			throw new RegraDeNegocioException("Favor informar um CPF válido.");
		}
		
		
		
	}

	@Override
	public void validarNascimento(Pessoa pessoa) {
		
		int idadeAtual = calcularIdade(pessoa.getNascimento());
		System.out.print(idadeAtual);
		try {
			if (idadeAtual < 18) {
				throw new RegraDeNegocioException("Cadastro apenas para maiores de 18 anos.");
			}
		} catch (Exception e) {
			throw new RegraDeNegocioException("Cadastro apenas para maiores de 18 anos.");
		}
		
		
	}

	@Override
	public void validarQuantidadeContato(Pessoa pessoa) {
		try {
			if (pessoa.getContatos().size() <  1){
				throw new RegraDeNegocioException("Você deve informar pelo menos 1 contato.");
			}
			
			for (Contato contato : pessoa.getContatos()) {
				if (contato.getValor().isEmpty()) {
					throw new RegraDeNegocioException("Certifique-se de que os contatos estão preenchidos corretamente.");
				}
			}
		} catch (Exception e) {
			throw new RegraDeNegocioException(e.toString());
		}
		
	}

	@Override
	@Transactional
	public List<Pessoa> buscar(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	
	public int calcularIdade(LocalDate nascimento) {
		
		LocalDate now = LocalDate.now();
		
		return now.getYear() - nascimento.getYear();
	}


	@Override
	public void validarEmail(List<Contato> contatos) {
		for (Contato contato : contatos) {
			if (contato.getTipo().equals(TipoContato.EMAIL)) {
				if (!isValid(contato.getValor())) {
					throw new RegraDeNegocioException("Certifique-se de que os contatos estão preenchidos corretamente.");
				}
			}
		}
	}

	
	static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
	
	
	
}
