package com.projetoopotimum.optimum.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "pessoa")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pessoa extends BaseEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	@CPF
	private String cpf;
	
	@Column(name = "nascimento")
	private LocalDate nascimento;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "cep")
	private String cep;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pessoa_contato", 
    		joinColumns = @JoinColumn(name = "contato_id", referencedColumnName = "id" ), 
    		inverseJoinColumns = @JoinColumn(name = "pessoa_id",referencedColumnName = "id"))
    private List<Contato> contatos;
	
}
