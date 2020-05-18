package com.projetoopotimum.optimum.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.repository.PessoaRepository;
import com.projetoopotimum.optimum.service.PessoaService;

@RestController("/api/pessoas")
@CrossOrigin(origins = {"http://localhost:4200", "https://optimum-frontend.herokuapp.com"})
public class PessoaController {

	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}


	@GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas() {

        List<Pessoa> students = pessoaService.listarPessoas();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Pessoa> listarTodasPessoas(@RequestBody Pessoa pessoa) {
 
        Pessoa ps = pessoaService.salvarPessoa(pessoa);
        return new ResponseEntity<>(ps, HttpStatus.CREATED);
    }
	
	@GetMapping("/busca")
	public ResponseEntity buscar(
			@RequestParam(value = "nome") String nome) {
		List<Pessoa> pessoas = pessoaService.buscar(nome);
		return ResponseEntity.ok(pessoas);
	}
	
}
