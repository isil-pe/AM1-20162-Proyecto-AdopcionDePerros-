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

import java.util.List;

public class MisPerrosActivity extends AppCompatActivity {
    private ListView lstPets;
    private List<PerroEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_perros);
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
        lstPets= (ListView)findViewById(R.id.lsvPerro);
    }

    private void events() {
        lstPets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PerroEntity perroEntity= (PerroEntity) parent.getAdapter().getItem(position);
                gotoEditarPerro(perroEntity);
            }
        });

    }

    private void gotoEditarPerro(PerroEntity perroEntity){
        Bundle bundle = new Bundle();
        bundle.putSerializable("Edit", perroEntity);

        Intent intent = new Intent(this, EditarPerroActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
