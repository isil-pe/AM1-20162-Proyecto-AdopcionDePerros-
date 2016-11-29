package com.example.dvasq.adopciondeperros;

import android.app.Application;

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
    private UsuarioEntity currentUser;

    public UsuarioEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UsuarioEntity currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        lstPerro = new ArrayList<>();
        lstUser = new ArrayList<>();

        PerroEntity p1 = new PerroEntity(1,"Jake","Boxer","M",3,"Large","Disponible",1);
        PerroEntity p2 = new PerroEntity(2,"Tiffany","Boxer","F",2,"Large","Disponible",1);
        PerroEntity p3 = new PerroEntity(3,"Daisy","Maltese","F",4,"Teacup","Disponible",2);
        PerroEntity p4 = new PerroEntity(4,"Snowball","Mix","M",6,"Small","Disponible",2);
        PerroEntity p5 = new PerroEntity(5,"Ringo","Mix","M",1,"Large","Disponible",3);

        UsuarioEntity u1 = new UsuarioEntity(1,"Admin","Admin","Daniel Vasquez Fernandez",
                "941-773-926","dvasquez447@yahoo.com");
        UsuarioEntity u2 = new UsuarioEntity(2,"User","User","Guillermo Cusihuaman",
                "754-326-951","usuario2@gmail.com");
        UsuarioEntity u3 = new UsuarioEntity(3,"Admin","Admin","Daniel Vasquez Fernandez",
                "654-946-632","usuario3@hotmail.com");

        lstPerro.add(p1);
        lstPerro.add(p2);
        lstPerro.add(p3);
        lstPerro.add(p4);
        lstPerro.add(p5);

        lstUser.add(u1);
        lstUser.add(u2);
        lstUser.add(u3);
    }

    public void addPerro(PerroEntity perroEntity){lstPerro.add(perroEntity);}
    public void addUsuario(UsuarioEntity usuarioEntity){lstUser.add(usuarioEntity);}

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

    public UsuarioEntity findUserbyId(int userId){
        int position=-1;
        UsuarioEntity user = null;
        for(int i = 0; i<this.lstUser.size(); i++){
            user = lstUser.get(i);
            if(user.getIdUsuario()== userId){
                break;
            }
        }
        return user;
    }

    public List<PerroEntity> allPerros()
    {
        return this.lstPerro;
    }
    public List<UsuarioEntity> allUsers(){return this.lstUser;}

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
            usuario = lstUser.get(i);
            if(usuario.getUsername().equals(user) && usuario.getPassword().equals(pass)){
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
