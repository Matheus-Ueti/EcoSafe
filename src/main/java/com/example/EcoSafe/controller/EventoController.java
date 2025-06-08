package com.example.EcoSafe.controller;

import com.example.EcoSafe.dto.EventoRequest;
import com.example.EcoSafe.model.Evento;
import com.example.EcoSafe.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<Page<Evento>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Evento> eventos = eventoService.listarTodos(PageRequest.of(page, size));
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        Evento evento = eventoService.buscarPorId(id);
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@Valid @RequestBody EventoRequest request) {
        Evento novoEvento = eventoService.criarEvento(request);
        return ResponseEntity.ok(novoEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @Valid @RequestBody EventoRequest request) {
        Evento eventoAtualizado = eventoService.atualizarEvento(id, request);
        return ResponseEntity.ok(eventoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<Page<Evento>> buscarPorTipo(
            @RequestParam String tipoEvento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Evento> eventos = eventoService.buscarPorTipo(tipoEvento, PageRequest.of(page, size));
        return ResponseEntity.ok(eventos);
    }
}