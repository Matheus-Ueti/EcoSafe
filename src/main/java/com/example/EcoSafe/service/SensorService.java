package com.example.EcoSafe.service;

import com.example.EcoSafe.dto.SensorRequest;
import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.model.Sensor;
import com.example.EcoSafe.repository.LocalRepository;
import com.example.EcoSafe.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private LocalRepository localRepository;

    public Sensor criarSensor(SensorRequest request) {
        // Inicializar o sensor
        Sensor sensor = new Sensor();
        sensor.setTipo(request.getTipo());
        sensor.setUnidadeMedida(request.getUnidadeMedida());
        sensor.setStatus(request.getStatus());
        
        // Tentar buscar a localização, mas não falhar se não encontrar
        if (request.getLocalizacaoId() != null) {
            try {
                Optional<Local> localOpt = localRepository.findById(request.getLocalizacaoId());
                localOpt.ifPresent(sensor::setLocalizacao);
            } catch (Exception e) {
                // Log do erro, mas continua sem localização
                System.out.println("Não foi possível associar à localização: " + e.getMessage());
            }
        }
        
        // Salvar o sensor mesmo sem localização
        return sensorRepository.save(sensor);
    }

    public Sensor buscarPorId(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));
    }

    public Page<Sensor> listarTodos(Pageable pageable) {
        return sensorRepository.findAll(pageable);
    }

    public Page<Sensor> buscarPorTipo(String tipo, Pageable pageable) {
        return sensorRepository.findByTipoContainingIgnoreCase(tipo, pageable);
    }

    public Page<Sensor> buscarPorStatus(String status, Pageable pageable) {
        return sensorRepository.findByStatus(status, pageable);
    }

    public Sensor atualizarSensor(Long id, Sensor sensorAtualizado) {
        Sensor sensor = buscarPorId(id);
        sensor.setTipo(sensorAtualizado.getTipo());
        sensor.setLocalizacao(sensorAtualizado.getLocalizacao());
        sensor.setUnidadeMedida(sensorAtualizado.getUnidadeMedida());
        sensor.setStatus(sensorAtualizado.getStatus());
        return sensorRepository.save(sensor);
    }

    public void deletarSensor(Long id) {
        sensorRepository.deleteById(id);
    }
}