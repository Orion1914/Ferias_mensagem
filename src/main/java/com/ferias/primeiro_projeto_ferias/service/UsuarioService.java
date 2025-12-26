package com.ferias.primeiro_projeto_ferias.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ferias.primeiro_projeto_ferias.model.Usuario;
import com.ferias.primeiro_projeto_ferias.repository.UsuarioRepository;
import java.util.Optional;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();

  }

  public Usuario registrarUsuario(String username, String password) {
    String senhaCriptografada = passwordEncoder.encode(password);
    Usuario usuario = new Usuario(username, senhaCriptografada);
    return usuarioRepository.save(usuario);
  }

  public Optional<Usuario> buscarPorUsername(String username) {
    return usuarioRepository.findByUsername(username);
  }
}