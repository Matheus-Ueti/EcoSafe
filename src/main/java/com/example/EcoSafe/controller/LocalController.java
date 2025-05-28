package com.example.EcoSafe.controller;

import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/locais")
@CrossOrigin(origins = "*")
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public ResponseEntity<Page<Local>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Local> locais = localService.listarTodos(PageRequest.of(page, size));
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> buscarPorId(@PathVariable Long id) {
        Local local = localService.buscarPorId(id);
        return ResponseEntity.ok(local);
    }

    @PostMapping
    public ResponseEntity<Local> criarLocal(@Valid @RequestBody Local local) {
        Local novoLocal = localService.criarLocal(local);
        return ResponseEntity.ok(novoLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> atualizarLocal(@PathVariable Long id, @Valid @RequestBody Local local) {
        Local localAtualizado = localService.atualizarLocal(id, local);
        return ResponseEntity.ok(localAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocal(@PathVariable Long id) {
        localService.deletarLocal(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar/cidade")
    public ResponseEntity<Page<Local>> buscarPorCidade(
            @RequestParam String cidade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Local> locais = localService.buscarPorCidade(cidade, PageRequest.of(page, size));
        return ResponseEntity.ok(locais);
    }
} 