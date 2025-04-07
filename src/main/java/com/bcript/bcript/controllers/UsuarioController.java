package com.bcript.bcript.controllers;

import com.bcript.bcript.dto.UsuarioDTO;
import com.bcript.bcript.entities.Usuario;
import com.bcript.bcript.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO dto) {
        // Chama o serviço para salvar um novo usuário no sistema, pegando os dados.
        dto = usuarioService.salvarUsuario(dto);
        // Retorna uma resposta HTTP com status 200 (OK) e os dados do usuário que foi cadastrado.
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> logar(@RequestBody UsuarioDTO dto){
        // Chama o serviço para verificar se o login do usuário (email e senha) é válido.
        boolean test = usuarioService.login(dto);
        // Se o login for bem-sucedido (o serviço retorna 'true'), retorna uma resposta HTTP com status 200 (OK)
        if(test){
            return ResponseEntity.ok("Sucesso !");
        }
        // Se o login falhar (o serviço retorna 'false'), retorna uma resposta HTTP com status 401 .

        return ResponseEntity.status(401).body("Senha ou Login incorreto !");
    }
}


