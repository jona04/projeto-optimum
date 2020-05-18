package com.projetoopotimum.optimum.model.repository;

import com.projetoopotimum.optimum.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNome(String username);

    boolean existsByNome(String nome);
}
