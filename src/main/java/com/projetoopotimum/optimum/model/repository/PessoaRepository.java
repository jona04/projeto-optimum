package com.projetoopotimum.optimum.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoopotimum.optimum.model.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	
	
}
