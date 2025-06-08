package com.example.EcoSafe.controller;
import com.example.EcoSafe.dto.SensorRequest;
import com.example.EcoSafe.model.Sensor;
import com.example.EcoSafe.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/sensores")
@CrossOrigin(origins = "*")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<Page<Sensor>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Sensor> sensores = sensorService.listarTodos(PageRequest.of(page, size));
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> buscarPorId(@PathVariable Long id) {
        Sensor sensor = sensorService.buscarPorId(id);
        return ResponseEntity.ok(sensor);
    }

    @PostMapping
    public ResponseEntity<Sensor> criarSensor(@Valid @RequestBody SensorRequest request) {
        Sensor novoSensor = sensorService.criarSensor(request);
        return ResponseEntity.ok(novoSensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> atualizarSensor(@PathVariable Long id, @Valid @RequestBody Sensor sensor) {
        Sensor sensorAtualizado = sensorService.atualizarSensor(id, sensor);
        return ResponseEntity.ok(sensorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSensor(@PathVariable Long id) {
        sensorService.deletarSensor(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<Page<Sensor>> buscarPorTipo(
            @RequestParam String tipo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Sensor> sensores = sensorService.buscarPorTipo(tipo, PageRequest.of(page, size));
        return ResponseEntity.ok(sensores);
    }
} 