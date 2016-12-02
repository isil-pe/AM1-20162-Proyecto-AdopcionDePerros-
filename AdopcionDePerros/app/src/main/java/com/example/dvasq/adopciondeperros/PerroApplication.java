package com.example.dvasq.adopciondeperros;

import android.app.Application;

import com.example.dvasq.adopciondeperros.model.FavoriteEntity;
import com.example.dvasq.adopciondeperros.model.PerroEntity;
import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno-J on 24/11/2016.
 */
public class PerroApplication extends Application {
    private List<PerroEntity> lstPerro;
    private List<UsuarioEntity> lstUser;
    private List<FavoriteEntity> lstFavorite;
    private UsuarioEntity currentUser;
    private List<PerroEntity> lstFavPerros;

    @Override
    public void onCreate(){
        super.onCreate();
        lstPerro = new ArrayList<>();
        lstUser = new ArrayList<>();
        lstFavorite = new ArrayList<>();
        lstFavPerros = new ArrayList<>();

        PerroEntity p1 = new PerroEntity(1,"Jake","Boxer","M",3,"Large","Disponible",1);
        PerroEntity p2 = new PerroEntity(2,"Tiffany","Boxer","H",2,"Large","Disponible",1);
        PerroEntity p3 = new PerroEntity(3,"Daisy","Maltese","H",4,"Teacup","Disponible",2);
        PerroEntity p4 = new PerroEntity(4,"Snowball","Mix","M",6,"Small","Disponible",2);
        PerroEntity p5 = new PerroEntity(5,"Ringo","Mix","M",1,"Large","Disponible",3);

        UsuarioEntity u1 = new UsuarioEntity(1,"Admin","Admin","Daniel Vasquez Fernandez",
                "941-773-926","dvasquez447@yahoo.com");
        UsuarioEntity u2 = new UsuarioEntity(2,"User","User","Guillermo Cusihuaman",
                "754-326-951","usuario2@gmail.com");
        UsuarioEntity u3 = new UsuarioEntity(3,"abcd","abcd","John Risinger Fernandez",
                "654-946-632","usuario3@hotmail.com");

        FavoriteEntity f1 = new FavoriteEntity(1,1);
        FavoriteEntity f2 = new FavoriteEntity(1,2);
        FavoriteEntity f3 = new FavoriteEntity(1,4);
        FavoriteEntity f4 = new FavoriteEntity(2,3);
        FavoriteEntity f5 = new FavoriteEntity(2,5);
        FavoriteEntity f6 = new FavoriteEntity(3,1);
        FavoriteEntity f7 = new FavoriteEntity(3,2);
        FavoriteEntity f8 = new FavoriteEntity(3,3);

        lstPerro.add(p1);
        lstPerro.add(p2);
        lstPerro.add(p3);
        lstPerro.add(p4);
        lstPerro.add(p5);

        lstUser.add(u1);
        lstUser.add(u2);
        lstUser.add(u3);

        lstFavorite.add(f1);
        lstFavorite.add(f2);
        lstFavorite.add(f3);
        lstFavorite.add(f4);
        lstFavorite.add(f5);
        lstFavorite.add(f6);
        lstFavorite.add(f7);
        lstFavorite.add(f8);

        lstFavPerros = findFavorite(1);
    }

    public UsuarioEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UsuarioEntity currentUser) {
        this.currentUser = currentUser;
    }

    public void addPerro(PerroEntity perroEntity){lstPerro.add(perroEntity);}
    public void addUsuario(UsuarioEntity usuarioEntity){lstUser.add(usuarioEntity);}
    public void addFavorite(FavoriteEntity favoriteEntity){lstFavorite.add(favoriteEntity);}

    public void removePetById(int perroId){
        int position = -1;
        PerroEntity perro;

        for (int i = 0; i <this.lstPerro.size() ; i++){
            perro = lstPerro.get(i);
            if(perro.getPerroId()==perroId){
                position = i;
                break;
            }
        }
        if(position>=0 && position<lstPerro.size())
        {
            this.lstPerro.remove(position);
        }
    }

    public void removeFavorite(int userId, int perroId){
        int position = -1;
        FavoriteEntity fav;

        for(int i = 0; i < this.lstFavorite.size(); i++){
            fav = lstFavorite.get(i);
            if(fav.getUserId()==userId && fav.getPerroId()==perroId){
                position = i;
                break;
            }
        }
        if(position>=0 && position<lstFavorite.size()){
            this.lstFavorite.remove(position);
        }
    }

    public void updatePerro(int position, PerroEntity perroEntity)
    {
        if(position<lstPerro.size())
        {
            lstPerro.set(position, perroEntity);
        }
    }

    public void updatePerroById(int perroId, PerroEntity perroEntity)
    {
        int position=-1;
        PerroEntity perro;
        for (int i = 0; i <this.lstPerro.size() ; i++) {
            perro= lstPerro.get(i);
            if(perro.getPerroId()==perroId)
            {
                position=i;
                break;
            }
        }
        if(position>=0 && position<lstPerro.size())
        {
            this.lstPerro.set(position, perroEntity);
        }
    }

    public boolean findFavoriteExist(int userId, int perroId){
        for(FavoriteEntity f : lstFavorite){
            if(f.getUserId()==userId && f.getPerroId()==perroId){
                return true;
            }
        }
        return false;
    }

    public PerroEntity findPerrobyId(int perroId){
        PerroEntity p = null;
        for(int i = 0; i<this.lstPerro.size();i++){
            p = lstPerro.get(i);
            if(p.getPerroId()==perroId){
                break;
            }
        }
        return p;
    }

    public List<PerroEntity> findFavorite(int currentUserId){
        lstFavorite = new ArrayList<>();
        List<PerroEntity>lstFav = new ArrayList<>();
        PerroEntity perroEntity;
        for(FavoriteEntity f : lstFavorite){
            if(f.getUserId()==currentUserId){
                perroEntity = findPerrobyId(f.getPerroId());
                lstFav.add(perroEntity);
            }
        }
        return lstFav;
    }

    public UsuarioEntity findUserbyId(int userId){
        UsuarioEntity user = null;
        for(int i = 0; i<this.lstUser.size(); i++){
            user = lstUser.get(i);
            if(user.getIdUsuario()== userId){
                break;
            }
        }
        return user;
    }

    public List<PerroEntity> allFavorite( ){
        //lstFavorite = new ArrayList<>();
        List<PerroEntity>lstFav = new ArrayList<>();
        PerroEntity perroEntity;
        for(FavoriteEntity f : lstFavorite){
                perroEntity = findPerrobyId(f.getPerroId());
                lstFav.add(perroEntity);
        }
        return lstFav;
    }

    public List<PerroEntity> allPerros()
    {
        return this.lstPerro;
    }
    public List<UsuarioEntity> allUsers(){return this.lstUser;}
    public List<PerroEntity> allFav(){return this.lstFavPerros;}

    public int countPerros()
    {
        return this.lstPerro.size();
    }
    public PerroEntity lastPerro()
    {
        if(this.lstPerro.size()>0)
        {
            return this.lstPerro.get(this.lstPerro.size()-1);
        }
        return null;
    }

    public UsuarioEntity lastUser(){
        if(this.lstUser.size()>0){
            return this.lstUser.get(this.lstUser.size()-1);
        }
        return null;
    }

    public UsuarioEntity validar(String user, String pass){
        int position = -1;
        UsuarioEntity usuario = null;

        for (int i = 0; i <this.lstUser.size() ; i++){
            UsuarioEntity u = lstUser.get(i);
            if(u.getUsername().equals(user) && u.getPassword().equals(pass)){
                position = i;
                break;
            }
        }
        if(position>=0 && position<lstUser.size())
        {
            usuario = lstUser.get(position);
        }
        return usuario;
    }

    public boolean validarUsuario(String user){
        Boolean bool = false;
        UsuarioEntity usuario;
        for (int i = 0; i <this.lstUser.size() ; i++){
            usuario = lstUser.get(i);
            if(usuario.getUsername().equals(user)){
                bool = false;
                break;
            }
            else{
                bool = true;
            }
        }
        return bool;
    }
}
