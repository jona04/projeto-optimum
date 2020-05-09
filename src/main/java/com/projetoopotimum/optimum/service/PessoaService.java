package com.projetoopotimum.optimum.service;

import java.time.LocalDate;
import java.util.List;

import com.projetoopotimum.optimum.exception.RegraDeNegocioException;
import com.projetoopotimum.optimum.model.entity.Contato;
import com.projetoopotimum.optimum.model.entity.Pessoa;

public interface PessoaService {
	
	Pessoa salvarPessoa(Pessoa pessoa, Contato contato);
	
	void validarCPF(Pessoa pessoa);
	
	void validarNascimento(Pessoa pessoa);
	
	void validarQuantidadeContato(Contato contato);
	
	List<Pessoa> buscar( Pessoa pessoaFiltro );
	
}
