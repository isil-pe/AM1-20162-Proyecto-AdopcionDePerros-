package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dvasq.adopciondeperros.adapter.PerroAdapter;
import com.example.dvasq.adopciondeperros.model.PerroEntity;
import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private ListView lstPets;
    private List<PerroEntity> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
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
        PerroApplication application = (PerroApplication)getApplication();
        //UsuarioEntity current = application.getCurrentUser();
        data= application.allPerros();
    }

    private void ui() {
        lstPets= (ListView)findViewById(R.id.lsvFavPerro);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("Perro",perroEntity);

        Intent intent = new Intent(this,PerroDetalleActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
