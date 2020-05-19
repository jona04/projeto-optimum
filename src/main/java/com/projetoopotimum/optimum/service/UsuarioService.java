package com.projetoopotimum.optimum.service;

import com.projetoopotimum.optimum.exception.UsuarioCadastradoException;
import com.projetoopotimum.optimum.model.entity.Usuario;
import com.projetoopotimum.optimum.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        if (existeUsuario(usuario.getNome())) {
            throw new UsuarioCadastradoException(usuario.getNome());
        }
        return usuarioRepository.save(usuario);
    }

    public boolean existeUsuario(String nome){
        return usuarioRepository.existsByNome(nome);
    }

    public Optional<Usuario> findUsuario(String nome){
        return usuarioRepository.findByNome(nome);
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByNome(nome)
                .orElseThrow(()->new UsernameNotFoundException("Login nao encontrado"));

        return User
                .builder()
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }

}
