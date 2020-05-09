package com.projetoopotimum.optimum.api.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PessoaDTO {

	
	private Integer id;
	private String nome;
	private String cpf;
	private LocalDate nascimento;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	private List<ContatoDTO> contatos;
	
	
}
