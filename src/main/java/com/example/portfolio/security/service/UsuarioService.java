package com.example.portfolio.security.service;

import com.example.portfolio.security.entities.Usuario;
import com.example.portfolio.security.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    final
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> getByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}