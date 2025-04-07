package com.bcript.bcript.dto;

import com.bcript.bcript.entities.Usuario;

public class UsuarioDTO {

    private Long id;
    private String Senha;
    private String Email;

    public UsuarioDTO() {
    }
    public UsuarioDTO(Long id, String senha, String email) {
        this.id = id;
        Senha = senha;
        Email = email;

    }
    public UsuarioDTO(Usuario Entity) {
        this.id = Entity.getId();
        Senha = Entity.getSenha();
        Email = Entity.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

