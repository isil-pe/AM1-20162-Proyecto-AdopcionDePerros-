package com.example.dvasq.adopciondeperros.model;

/**
 * Created by dvasq on 11/30/2016.
 */
public class FavoriteEntity {
    private int userId;
    private int perroId;

    public FavoriteEntity(int userId, int perroId) {
        this.userId = userId;
        this.perroId = perroId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPerroId() {
        return perroId;
    }

    public void setPerroId(int perroId) {
        this.perroId = perroId;
    }
}
