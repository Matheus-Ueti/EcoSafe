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

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private LocalRepository localRepository;

    public Sensor criarSensor(SensorRequest request) {
        Local local = localRepository.findById(request.getLocalizacaoId())
            .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        Sensor sensor = new Sensor();
        sensor.setTipo(request.getTipo());
        sensor.setLocalizacao(local);
        sensor.setUnidadeMedida(request.getUnidadeMedida());
        sensor.setStatus(request.getStatus());

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