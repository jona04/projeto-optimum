package com.projetoopotimum.optimum.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetoopotimum.optimum.exception.RegraDeNegocioException;
import com.projetoopotimum.optimum.model.entity.Contato;
import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.repository.ContatoRepository;
import com.projetoopotimum.optimum.model.repository.PessoaRepository;
import com.projetoopotimum.optimum.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	
	PessoaRepository pessoaRepository;
	ContatoRepository contatoRepository;
	
	public PessoaServiceImpl(PessoaRepository pessoaRepository, ContatoRepository contatoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.contatoRepository = contatoRepository;
	}
	
	
	@Override
	public Pessoa salvarPessoa(Pessoa pessoa, Contato contato) {
		validarCPF(pessoa.getCpf());
		validarNascimento(pessoa.getNascimento());
		validarQuantidadeContato(contato);
		pessoaRepository.save(pessoa);
		contatoRepository.save(contato);
		
		return pessoa;
	}

	@Override
	public void validarCPF(String cpf){
		
		if (cpf.length() < 11) {
			throw new RegraDeNegocioException("Favor informar um CPF válido.");
		}
		
		
	}

	@Override
	public void validarNascimento(LocalDate nascimento) {
		int idadeAtual = calcularIdade(nascimento);
		if (idadeAtual < 18) {
			throw new RegraDeNegocioException("Cadastro apenas para maiores de 18 anos.");
		}
		
	}

	@Override
	public void validarQuantidadeContato(Contato contato) {
		if (contato == null) {
			throw new RegraDeNegocioException("Você deve informar pelo menos 1 contato.");
		}
	}

	@Override
	public List<Pessoa> buscar(Pessoa pessoaFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int calcularIdade(LocalDate nascimento) {
		
		LocalDate now = LocalDate.now();
		
		return now.getYear() - nascimento.getYear();
	}
	
	
}
