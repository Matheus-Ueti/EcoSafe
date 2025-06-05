package com.example.EcoSafe.controller;

import com.example.EcoSafe.config.JwtTokenUtil;
import com.example.EcoSafe.dto.RegisterRequest;
import com.example.EcoSafe.dto.LoginRequest;
import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);

            Optional<Usuario> usuario = usuarioService.buscarPorEmail(loginRequest.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("tipo", "Bearer");
            if (usuario.isPresent()) {
                response.put("usuario", Map.of(
                    "id", usuario.get().getIdUsuario(),
                    "nome", usuario.get().getNome(),
                    "email", usuario.get().getEmail()
                ));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Credenciais inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registro(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            Usuario novoUsuario = usuarioService.criarUsuario(registerRequest);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Usuário criado com sucesso");
            response.put("usuario", Map.of(
                "id", novoUsuario.getIdUsuario(),
                "nome", novoUsuario.getNome(),
                "email", novoUsuario.getEmail(),
                "cpf", novoUsuario.getCpf(),
                "localizacao", novoUsuario.getLocalizacao() != null ? 
                    novoUsuario.getLocalizacao().getNome() : null
            ));

            UserDetails userDetails = userDetailsService.loadUserByUsername(novoUsuario.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);
            response.put("token", token);
            response.put("tipo", "Bearer");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Erro ao criar usuário: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}