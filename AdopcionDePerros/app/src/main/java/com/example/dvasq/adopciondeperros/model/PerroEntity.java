package com.example.dvasq.adopciondeperros.model;

import java.io.Serializable;

/**
 * Created by dvasq on 11/24/2016.
 */
public class PerroEntity implements Serializable {
    private int PerroId;
    private String name;
    private String race;
    private String gender;
    private int age;
    private String size;
    private String state;
    private int image;

    public PerroEntity(int perroId, String name, String race, String gender, int age, String size, String state, int image) {
        PerroId = perroId;
        this.name = name;
        this.race = race;
        this.gender = gender;
        this.age = age;
        this.size = size;
        this.state = state;
        this.image = image;
    }

    public PerroEntity(int perroId, String name, String race, String gender, int age, String size, String state) {
        PerroId = perroId;
        this.name = name;
        this.race = race;
        this.gender = gender;
        this.age = age;
        this.size = size;
        this.state = state;
    }

    public int getPerroId() {
        return PerroId;
    }

    public void setPerroId(int perroId) {
        PerroId = perroId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getEstado() {
        return state;
    }

    public void setEstado(String estado) {
        this.state = estado;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
