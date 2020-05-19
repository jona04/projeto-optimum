package com.projetoopotimum.optimum.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetoopotimum.optimum.model.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query(value = "SELECT * FROM pessoa "
			+ "WHERE id_usuario = :id ",nativeQuery = true)
	List<Pessoa> findByUsuario(Integer id);

	@Query(value = "SELECT * FROM pessoa "
			+ "WHERE nome ILIKE %:nome% "
			+ "or endereco ILIKE %:nome% "
			+ "or cidade ILIKE %:nome% "
			+ "or bairro ILIKE %:nome% "
			+ "or estado ILIKE %:nome% "
			+ "or cep ILIKE %:nome% "
			+ "or cpf ILIKE %:nome%",nativeQuery = true)
	List<Pessoa> findByNome(String nome);
	
}
