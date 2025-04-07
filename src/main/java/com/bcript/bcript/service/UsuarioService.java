package com.bcript.bcript.service;

import com.bcript.bcript.dto.UsuarioDTO;
import com.bcript.bcript.entities.Usuario;
import com.bcript.bcript.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(dto.getEmail());

        // Pega a senha do objeto UsuarioDTO, codifica ela usando o PasswordEncoder
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        usuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }

    public boolean login(UsuarioDTO dto) {
        // Busca no banco de dados um usuário com o email fornecido no objeto UsuarioDTO.
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());

        // Se nenhum usuário com esse email for encontrado no banco de dados,o login falha, e retorna 'false'.//
        if (usuario == null) {
            return false;
        }

        // Se um usuário com o email foi encontrado, compara a senha fornecida no objeto UsuarioDTO (a senha que o usuário digitou) com a senha codificada
        // que está armazenada no objeto Usuario (vinda do banco de dados).
        // O 'passwordEncoder.matches()' faz essa comparação de forma segura,
        // verificando se a senha digitada, após ser codificada com o mesmo algoritmo,
        // corresponde à senha codificada armazenada.
        return passwordEncoder.matches(dto.getSenha(), usuario.getSenha());
    }
}