package com.projetoopotimum.optimum.api.controller;

import com.projetoopotimum.optimum.exception.UsuarioCadastradoException;
import com.projetoopotimum.optimum.model.entity.Usuario;
import com.projetoopotimum.optimum.model.repository.UsuarioRepository;
import com.projetoopotimum.optimum.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        try {
            return usuarioService.salvar(usuario);
        }catch(UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @GetMapping("{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Usuario obterPorId(@PathVariable Integer id){
//        return repository
//                .findById(id)
//                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado") );
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletar(@PathVariable Integer id){
//        repository
//                .findById(id)
//                .map( usuario -> {
//                    repository.delete(usuario);
//                    return Void.TYPE;
//                })
//                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
//    }
//
//    @PutMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void atualizar(@PathVariable Integer id, @RequestBody Usuario usuarioAtualizado){
//        repository
//                .findById(id)
//                .map( usuario -> {
//                    usuarioAtualizado.setId(usuario.getId());
//                    return repository.save(usuarioAtualizado);
//                })
//                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
//    }

}
