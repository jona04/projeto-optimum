package com.projetoopotimum.optimum.service;

import java.time.LocalDate;
import java.util.List;

import com.projetoopotimum.optimum.exception.RegraDeNegocioException;
import com.projetoopotimum.optimum.model.entity.Contato;
import com.projetoopotimum.optimum.model.entity.Pessoa;

public interface PessoaService {
	
	Pessoa salvarPessoa(Pessoa pessoa);
	
	void validarCPF(Pessoa pessoa);
	
	void validarNascimento(Pessoa pessoa);
	
	void validarQuantidadeContato(Pessoa pessoa);
	
	public List<Pessoa> listarPessoas(String nome);
	
	List<Pessoa> buscar( String nome );
	
}
