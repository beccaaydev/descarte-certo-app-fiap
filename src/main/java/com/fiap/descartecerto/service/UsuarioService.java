package com.fiap.descartecerto.service;

import com.fiap.descartecerto.dto.UsuarioDTO;
import com.fiap.descartecerto.dto.UsuarioImpactoDTO;
import com.fiap.descartecerto.model.Usuario;
import com.fiap.descartecerto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        String emailLimpo = usuarioDTO.getEmail().trim().toLowerCase();

        if (usuarioRepository.findByEmail(emailLimpo).isPresent()) {
            throw new IllegalArgumentException("O e-mail '" + emailLimpo + "' já está em uso!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setCepUsuario(usuarioDTO.getCep());
        usuario.setTotalKgReciclado(usuarioDTO.getTotalKgReciclado());
        usuario.setTotalCo2Evitado(usuarioDTO.getTotalCo2Evitado());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return converterParaDTO(usuarioSalvo);
    }

    public UsuarioDTO obterUsuarioById(Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + idUsuario));
    }

    public UsuarioImpactoDTO obterImpactoUsuarioById(Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.map(u -> {
                    UsuarioImpactoDTO dto = new UsuarioImpactoDTO();

                    dto.setIdUsuario(u.getIdUsuario());
                    dto.setNome(u.getNome());
                    dto.setTotalKgReciclado(u.getTotalKgReciclado());
                    dto.setTotalCo2Evitado(u.getTotalCo2Evitado());

                    return dto;
                })
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + idUsuario));
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAllByOrderByIdUsuarioAsc()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obterUsuarioPorEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com email: " + email));
    }

    public UsuarioDTO atualizarUsuario(Long idUsuario, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + idUsuario));

        if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email já cadastrado no sistema");
            }
            usuario.setEmail(usuarioDTO.getEmail());
        }
        
        if (usuarioDTO.getNome() != null) {
            usuario.setNome(usuarioDTO.getNome());
        }
        if (usuarioDTO.getSenha() != null) {
            usuario.setSenha(usuarioDTO.getSenha());
        }
        if (usuarioDTO.getCep() != null) {
            usuario.setCepUsuario(usuarioDTO.getCep());
        }
        if (usuarioDTO.getTotalKgReciclado() != null) {
            usuario.setTotalKgReciclado(usuarioDTO.getTotalKgReciclado());
        }
        if (usuarioDTO.getTotalCo2Evitado() != null) {
            usuario.setTotalCo2Evitado(usuarioDTO.getTotalCo2Evitado());
        }
        
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return converterParaDTO(usuarioAtualizado);
    }

    public void deletarUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + idUsuario));
        usuarioRepository.delete(usuario);
    }

    private UsuarioDTO converterParaDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getCepUsuario(),
                usuario.getTotalKgReciclado(),
                usuario.getTotalCo2Evitado()
        );
    }
}

