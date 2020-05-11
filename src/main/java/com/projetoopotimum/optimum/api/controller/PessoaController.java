package com.projetoopotimum.optimum.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.repository.PessoaRepository;
import com.projetoopotimum.optimum.service.PessoaService;

@RestController
@CrossOrigin(origins = "https://optimum-frontend.herokuapp.com")
public class PessoaController {

	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> listarTodasPessoas() {

        List<Pessoa> students = pessoaService.listarPessoas();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
	
	@PostMapping("/pessoa")
    public ResponseEntity<Pessoa> listarTodasPessoas(@RequestBody Pessoa pessoa) {
 
        Pessoa ps = pessoaService.salvarPessoa(pessoa);
        return new ResponseEntity<>(ps, HttpStatus.CREATED);
    }
	
	@GetMapping("/pessoas/busca")
	public ResponseEntity buscar() {
		List<Pessoa> pessoas = pessoaRepository.findByNome("Jonatas");
		return ResponseEntity.ok(pessoas);
	}
	
}
