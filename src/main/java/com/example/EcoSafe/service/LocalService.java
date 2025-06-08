package com.example.EcoSafe.service;

import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public Local criarLocal(Local local) {
        return localRepository.save(local);
    }

    public Local buscarPorId(Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));
    }

    public Page<Local> listarTodos(Pageable pageable) {
        return localRepository.findAll(pageable);
    }

    public Page<Local> buscarPorCidade(String cidade, Pageable pageable) {
        return localRepository.findByCidadeContainingIgnoreCase(cidade, pageable);
    }

    public Local atualizarLocal(Long id, Local localAtualizado) {
        Local local = buscarPorId(id);
        local.setNome(localAtualizado.getNome());
        local.setCidade(localAtualizado.getCidade());
        local.setEstado(localAtualizado.getEstado());
        local.setCoordenadas(localAtualizado.getCoordenadas());
        return localRepository.save(local);
    }

    public void deletarLocal(Long id) {
        localRepository.deleteById(id);
    }
} 