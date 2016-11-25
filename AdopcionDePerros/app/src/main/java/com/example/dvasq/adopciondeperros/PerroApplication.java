package com.example.dvasq.adopciondeperros;

import android.app.Application;

import com.example.dvasq.adopciondeperros.model.PerroEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno-J on 24/11/2016.
 */
public class PerroApplication extends Application {
    private List<PerroEntity> lstPerro;

    @Override
    public void onCreate(){
        super.onCreate();
        lstPerro = new ArrayList<>();

        PerroEntity p1 = new PerroEntity(1,"Jake","Boxer","M",3,"Large","Disponible");
        PerroEntity p2 = new PerroEntity(2,"Tiffany","Boxer","F",2,"Large","Disponible");
        PerroEntity p3 = new PerroEntity(3,"Daisy","Maltese","F",4,"Teacup","Disponible");
        PerroEntity p4 = new PerroEntity(4,"Snowball","Mix","M",6,"Small","Disponible");
        PerroEntity p5 = new PerroEntity(5,"Ringo","Mix","M",1,"Large","Disponible");

        lstPerro.add(p1);
        lstPerro.add(p2);
        lstPerro.add(p3);
        lstPerro.add(p4);
        lstPerro.add(p5);
    }

    public void addPerro(PerroEntity perroEntity){lstPerro.add(perroEntity);}

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

    public List<PerroEntity> allPerros()
    {
        return this.lstPerro;
    }

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
}
