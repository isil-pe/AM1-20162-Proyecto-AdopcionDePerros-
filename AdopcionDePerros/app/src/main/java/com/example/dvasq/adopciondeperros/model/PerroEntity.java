package com.example.dvasq.adopciondeperros.model;

/**
 * Created by dvasq on 11/24/2016.
 */
public class PerroEntity {
    private int PerroId;
    private String name;
    private String race;
    private int age;
    private int weight;
    private int height;
    private boolean favorite;
    private int image;

    public PerroEntity(int perroId, String name, String race, int age, int weight, int height, boolean favorite, int image) {
        PerroId = perroId;
        this.name = name;
        this.race = race;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.favorite = favorite;
        this.image = image;
    }

    public PerroEntity(int perroId, String name, String race, int age, int weight, int height, boolean favorite) {
        PerroId = perroId;
        this.name = name;
        this.race = race;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.favorite = favorite;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
