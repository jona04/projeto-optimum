package com.projetoopotimum.optimum.api.controller;

import java.util.List;

import com.projetoopotimum.optimum.api.dto.PessoaDTO;
import com.projetoopotimum.optimum.exception.RegraDeNegocioException;
import com.projetoopotimum.optimum.model.entity.Usuario;
import com.projetoopotimum.optimum.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.repository.PessoaRepository;
import com.projetoopotimum.optimum.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {


	private final PessoaService pessoaService;
	private final UsuarioService usuarioService;

//	@Autowired
//	private PessoaRepository pessoaRepository;
//
	@GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas(
			@RequestParam(value = "nome") String nome
	) {

        List<Pessoa> pessoa = pessoaService.listarPessoas(nome);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }


	@PostMapping
    public ResponseEntity<Pessoa> salvar(
    		@RequestBody PessoaDTO dto,
			@RequestParam("nome") String nome) {
		Pessoa entidade = converter(dto,nome);
        Pessoa ps = pessoaService.salvarPessoa(entidade);
        return new ResponseEntity<>(ps, HttpStatus.CREATED);
    }
	
	@GetMapping("/busca")
	public ResponseEntity buscar(
			@RequestParam(value = "nome") String nome,
			@RequestParam(value = "nomeUsuario") String nomeUsuario) {
		List<Pessoa> pessoas = pessoaService.buscar(nome, nomeUsuario);
		return ResponseEntity.ok(pessoas);
	}

	private Pessoa converter(PessoaDTO dto, String nome) {
		Pessoa pessoa = new Pessoa();
		pessoa.setBairro(dto.getBairro());
		pessoa.setCep(dto.getCep());
		pessoa.setCidade(dto.getCidade());
		pessoa.setEndereco(dto.getEndereco());
		pessoa.setCpf(dto.getCpf());
		pessoa.setNascimento(dto.getNascimento());
		pessoa.setEstado(dto.getEstado());
		pessoa.setCep(dto.getCep());
		pessoa.setNome(dto.getNome());

		Usuario usuario = usuarioService
				.findUsuario(nome)
				.orElseThrow(()-> new RegraDeNegocioException("Usuário não encontrado com o nome informado"));

		pessoa.setUsuario(usuario);

		pessoa.setContatos(dto.getContatos());

		return pessoa;
	}

}
