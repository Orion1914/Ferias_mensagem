package com.ferias.primeiro_projeto_ferias.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import com.ferias.primeiro_projeto_ferias.service.UsuarioService;
import com.ferias.primeiro_projeto_ferias.model.Usuario;
import java.util.Optional;
import com.ferias.primeiro_projeto_ferias.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UsuarioService usuarioService;

  public AuthController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
    Usuario usuario = usuarioService.registrarUsuario(request.get("username"), "password");
    return ResponseEntity.ok(usuario);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    Optional<Usuario> usuario = usuarioService.buscarPorUsername(request.get("username"));
    if (usuario.isPresent() && usuario.get().getPassword().equals(request.get("password"))) {
      String token = JwtUtil.generateToken(usuario.get().getUsername());
      return ResponseEntity.ok(Map.of("token", token));
    } else {
      return ResponseEntity.status(401).body("Usuário ou senha inválidos");
    }

  }
}