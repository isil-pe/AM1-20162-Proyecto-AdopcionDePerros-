package com.example.dvasq.adopciondeperros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dvasq.adopciondeperros.adapter.PerroAdapter;
import com.example.dvasq.adopciondeperros.model.PerroEntity;

import java.util.List;

public class ListaPerroActivity extends AppCompatActivity {
    private TextView tviCount;
    private ListView lstPets;
    private List<PerroEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_perro);
        loadData();
        app();
    }

    private void app(){
        ui();
        events();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }

    private void populate() {
        PerroAdapter petAdapter = new PerroAdapter(this,data);
        lstPets.setAdapter(petAdapter);
    }

    private void loadData() {

        PerroApplication application= (PerroApplication)getApplication();
        data= application.allPerros();
    }

    private void ui() {
        tviCount = (TextView)findViewById(R.id.tviCount);
        lstPets= (ListView)findViewById(R.id.lsvPerro);
    }

    private void events() {
        lstPets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PerroEntity perroEntity= (PerroEntity) parent.getAdapter().getItem(position);
                gotoPerroDetail(perroEntity);
            }
        });

    }

    private void gotoPerroDetail(PerroEntity perroEntity){

    }
}
