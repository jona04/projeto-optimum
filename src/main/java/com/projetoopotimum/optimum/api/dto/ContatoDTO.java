package com.projetoopotimum.optimum.api.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContatoDTO {
	
	private Integer id;
	private String tipo;
	private String valor;
	
	
}
