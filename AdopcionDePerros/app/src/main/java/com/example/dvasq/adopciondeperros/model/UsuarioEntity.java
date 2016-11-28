package com.example.dvasq.adopciondeperros.model;

import java.io.Serializable;

/**
 * Created by Alumno-J on 24/11/2016.
 */
public class UsuarioEntity implements Serializable {
    private int idUsuario;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;

    public UsuarioEntity(int idUsuario, String username, String password, String name, String phone, String email) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
