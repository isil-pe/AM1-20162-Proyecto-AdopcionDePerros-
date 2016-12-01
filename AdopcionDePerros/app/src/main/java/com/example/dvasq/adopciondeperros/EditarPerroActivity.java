package com.example.dvasq.adopciondeperros;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dvasq.adopciondeperros.model.PerroEntity;

public class EditarPerroActivity extends Activity {
    private Button btnEdit, btnElim;
    private EditText eteNom, eteEdad, eteGenero, eteEstado, eteTamano, eteRaza;
    private String name, genero, estado, raza, tamano;
    private int edad;

    private PerroEntity perro;
    private PerroApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perro);
        extras();
        ui();
        events();
    }

    private void ui(){
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnElim = (Button)findViewById(R.id.btnElim);

        eteNom = (EditText)findViewById(R.id.eteEditNombre);
        eteEdad = (EditText)findViewById(R.id.eteEditEdad);
        eteEstado = (EditText)findViewById(R.id.eteEditEstado);
        eteGenero = (EditText)findViewById(R.id.eteEditGenero);
        eteRaza = (EditText) findViewById(R.id.eteEditRaza);
        eteTamano = (EditText) findViewById(R.id.eteEditTamano);

        if(perro!=null){
            String age = Integer.toString(perro.getAge());
            eteNom.setText(perro.getName());
            eteEdad.setText(age);
            eteGenero.setText(perro.getGender());
            eteEstado.setText(perro.getState());
            eteRaza.setText(perro.getRace());
            eteTamano.setText(perro.getSize());
        }
    }

    private void events(){
        application = (PerroApplication)getApplication();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerro();
                finish();
            }
        });

        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerro();
                finish();
            }
        });
    }

    private void updatePerro(){
        name = eteNom.getText().toString().trim();
        edad = Integer.parseInt(eteEdad.getText().toString().trim());
        genero = eteGenero.getText().toString().trim();
        estado = eteEstado.getText().toString().trim();
        raza = eteRaza.getText().toString().trim();
        tamano = eteTamano.getText().toString().trim();

        PerroEntity editPerro = new PerroEntity(perro.getPerroId(),name,raza,genero,edad,tamano,
                estado,perro.getIdUsuario());
        application.updatePerroById(perro.getPerroId(),editPerro);
    }

    private void deletePerro(){
        int perroId = perro.getPerroId();
        application.removePetById(perroId);
    }

    private void extras()
    {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            perro = (PerroEntity)bundle.getSerializable("Edit");
        }
    }

}
