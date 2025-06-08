package com.example.EcoSafe.service;

import com.example.EcoSafe.dto.EventoRequest;
import com.example.EcoSafe.model.Evento;
import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.repository.EventoRepository;
import com.example.EcoSafe.repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private LocalRepository localRepository;

    public Page<Evento> listarTodos(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
    }

    public Evento criarEvento(EventoRequest request) {
        Local local = localRepository.findById(request.getLocalId())
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));

        Evento evento = new Evento();
        evento.setTipoEvento(request.getTipoEvento());
        evento.setLocal(local);
        evento.setNivelRisco(request.getNivelRisco());
        evento.setDetalhes(request.getDetalhes());

        return eventoRepository.save(evento);
    }

    public Evento atualizarEvento(Long id, EventoRequest request) {
        Evento evento = buscarPorId(id);
        Local local = localRepository.findById(request.getLocalId())
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));

        evento.setTipoEvento(request.getTipoEvento());
        evento.setLocal(local);
        evento.setNivelRisco(request.getNivelRisco());
        evento.setDetalhes(request.getDetalhes());

        return eventoRepository.save(evento);
    }

    public void deletarEvento(Long id) {
        Evento evento = buscarPorId(id);
        eventoRepository.delete(evento);
    }

    public Page<Evento> buscarPorLocalId(Long localId, Pageable pageable) {
        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));
        return eventoRepository.findByLocal(local, pageable);
    }

    public Page<Evento> buscarPorTipo(String tipoEvento, Pageable pageable) {
        return eventoRepository.findByTipoEventoContainingIgnoreCase(tipoEvento, pageable);
    }
}