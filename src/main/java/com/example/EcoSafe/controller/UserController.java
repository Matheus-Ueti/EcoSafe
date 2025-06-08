package com.example.EcoSafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.service.UsuarioService;
import com.example.EcoSafe.dto.RegisterRequest;
import com.example.EcoSafe.dto.UsuarioResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastro")  // Alterado de "/users" para "/cadastro"
public class UserController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid RegisterRequest registerRequest){
        
        var usuario = usuarioService.criarUsuario(registerRequest);
        
        // Removemos a geração do token aqui
        // Retornamos apenas os dados do usuário cadastrado
        
        return ResponseEntity.ok(new UsuarioResponse(
            usuario.getId(), 
            usuario.getNome(), 
            usuario.getEmail()
        ));
    }
}