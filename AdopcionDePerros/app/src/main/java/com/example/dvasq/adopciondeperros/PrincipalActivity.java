package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {
    Button btnListar, btnPoner, btnFavorite, btnSalir, btnMisPerros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        app();
    }

    private void app(){
        ui();
        events();
    }

    private void ui(){
        btnListar = (Button)findViewById(R.id.btnlista);
        btnPoner = (Button)findViewById(R.id.btnponer);
        btnFavorite = (Button)findViewById(R.id.btnFavoritos);
        btnSalir = (Button)findViewById(R.id.btnsalir);
        btnMisPerros = (Button)findViewById(R.id.btnMisPerros);
    }

    private void events(){
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoList();
            }
        });
        btnPoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAgregar();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoInicio();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFavorite();
            }
        });
        btnMisPerros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMisPerros();
            }
        });
    }

    private void gotoList(){
        Intent intent = new Intent(this,ListaPerroActivity.class);
        startActivity(intent);
    }

    private void gotoAgregar(){
        Intent intent = new Intent(this,PublicarActivity.class);
        startActivity(intent);
    }

    private void gotoInicio(){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoFavorite(){
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    private void gotoMisPerros(){
        Intent intent = new Intent(this, MisPerrosActivity.class);
        startActivity(intent);
    }
}
