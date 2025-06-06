package br.com.fiap.money_control_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_control_api.model.User;
import br.com.fiap.money_control_api.dto.UserResponse;
import br.com.fiap.money_control_api.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        var userSaved = repository.save(user);
        return ResponseEntity.ok(new UserResponse(userSaved.getId(),userSaved.getEmail(), userSaved.getRole()));
    }

} 