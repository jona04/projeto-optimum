package com.projetoopotimum.optimum.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetoopotimum.optimum.model.entity.Pessoa;
import com.projetoopotimum.optimum.model.repository.PessoaRepository;
import com.projetoopotimum.optimum.service.PessoaService;

@RestController
public class PessoaController {

	
	@Autowired
	private PessoaService pessoaService;
	
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
	
//	@PostMapping
//	public ResponseEntity salvar(@RequestBody PessoaDTO dto) {
//		
//		
//		Pessoa pessoa = Pessoa.builder()
//				.nome(dto.getNome())
//				.cpf(dto.getCpf())
//				.nascimento(dto.getNascimento())
//				.endereço(dto.getEndereco())
//				.bairro(dto.getBairro())
//				.cidade(dto.getCidade())
//				.estado(dto.getEstado())
//				.cep(dto.getCep())
//				.build();
//		
//		int quantidadeContatos = dto.getContatos().size();
//		List<Contato> listaContatos = new ArrayList<Contato>()
//		for (int i = 0; i < quantidadeContatos; i++) {
//			Contato contato = Contato.builder()
//					.tipo(TipoContato.valueOf(dto.getContatos().get(i).getTipo()))
//					.valor(dto.getContatos().get(i).getValor())
//					.pessoa(pessoa)
//					.build();
//			
//			listaContatos.add(contato);
//		}
//		
//		
//		try {
//			Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa, listaContatos);
//			return new ResponseEntity(pessoaSalva, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//	
//	
	
}
