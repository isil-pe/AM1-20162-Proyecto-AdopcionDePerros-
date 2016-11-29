package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dvasq.adopciondeperros.model.PerroEntity;
import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

public class PerroDetalleActivity extends AppCompatActivity {
    Button btnFav, btnContact;
    TextView tviDeNom, tviDeEdad, tviDeRaza, tviDeEstado,tviDeTama, tviDeGenero;

    PerroEntity perroEntity;
    UsuarioEntity usuarioEntity;
    PerroApplication perroApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perro_detalle);

        extras();
        app();
    }

    private void app(){
        ui();

        if(perroEntity!=null){
            tviDeNom.setText(perroEntity.getName());
//            tviDeEdad.setText(perroEntity.getAge());
            tviDeRaza.setText(perroEntity.getRace());
            tviDeEstado.setText(perroEntity.getEstado());
            tviDeTama.setText(perroEntity.getSize());
            tviDeGenero.setText(perroEntity.getGender());
        }
        perroApplication=(PerroApplication)getApplication();

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementar funcionamiento de favoritos
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioEntity = perroApplication.findUserbyId(perroEntity.getIdUsuario());
                gotoContact(usuarioEntity);
            }
        });
    }

    private void ui(){
        tviDeNom = (TextView)findViewById(R.id.tviDetalleNombre);
        tviDeEdad = (TextView)findViewById(R.id.tviDetalleEdad);
        tviDeRaza = (TextView)findViewById(R.id.tviDetalleRace);
        tviDeEstado = (TextView)findViewById(R.id.tviDetalleEstado);
        tviDeTama = (TextView)findViewById(R.id.tviDetalleTama);
        tviDeGenero = (TextView)findViewById(R.id.tviDetalleGenero);

        btnFav = (Button)findViewById(R.id.btnFav);
        btnContact = (Button)findViewById(R.id.btnContact);
    }

    private void extras(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            perroEntity = (PerroEntity) bundle.getSerializable("Perro");
        }
    }

    private void gotoContact(UsuarioEntity usuarioEntity){
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", usuarioEntity);

        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
