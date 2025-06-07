package com.example.EcoSafe.service;

import com.example.EcoSafe.dto.RegisterRequest;
import com.example.EcoSafe.model.Usuario;
import com.example.EcoSafe.model.Local;
import com.example.EcoSafe.repository.UsuarioRepository;
import com.example.EcoSafe.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(RegisterRequest registerRequest) {
        // Verificar se email já existe
        if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }
        
        // Verificar se CPF já existe
        if (usuarioRepository.existsByCpf(registerRequest.getCpf())) {
            throw new RuntimeException("CPF já está em uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(registerRequest.getNome());
        usuario.setEmail(registerRequest.getEmail());
        usuario.setCpf(registerRequest.getCpf());
        usuario.setSenha(passwordEncoder.encode(registerRequest.getSenha()));
        
        if (registerRequest.getLocalizacaoId() != null) {
            Local local = localRepository.findById(registerRequest.getLocalizacaoId())
                    .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
            usuario.setLocalizacao(local);
        }
        
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Page<Usuario> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> buscarPorNome(String nome, Pageable pageable) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setCpf(usuarioAtualizado.getCpf());
        usuario.setLocalizacao(usuarioAtualizado.getLocalizacao());
        
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }
        
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}