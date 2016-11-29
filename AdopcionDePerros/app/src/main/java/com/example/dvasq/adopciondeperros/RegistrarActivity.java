package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

public class RegistrarActivity extends AppCompatActivity {
    EditText etxNom, etxApe, etxTele, etxEmail, etxUser, etxPass, etxConfirm;
    Button btnGuardar;

    String name, phone, email, username, password;
    UsuarioEntity usuarioEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ui();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                gotoPrincipal();
            }
        });
    }

    private void ui(){
        etxNom = (EditText)findViewById(R.id.etxnom);
        etxApe = (EditText)findViewById(R.id.etxape);
        etxTele = (EditText)findViewById(R.id.etxtele);
        etxEmail = (EditText)findViewById(R.id.etxemail);
        etxUser = (EditText)findViewById(R.id.etxuser);
        etxPass = (EditText)findViewById(R.id.etxpass);
        etxConfirm = (EditText)findViewById(R.id.etxcon);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
    }

    private void addUser(){
        PerroApplication application = (PerroApplication)getApplication();

        name = etxNom.getText().toString().trim() +" "+ etxApe.getText().toString().trim();
        phone = etxTele.getText().toString().trim();
        email = etxEmail.getText().toString().trim();
        username = etxUser.getText().toString().trim();
        password = etxPass.getText().toString().trim();

        usuarioEntity = application.lastUser();
        int userId;
        if(usuarioEntity!=null){
            userId = usuarioEntity.getIdUsuario()+1;
        }else{
            userId = 1;
        }

        UsuarioEntity entity = new UsuarioEntity(userId,username,password,name,phone,email);
        application.addUsuario(entity);
        application.setCurrentUser(entity);
    }

    private void gotoPrincipal(){
        Intent intent = new Intent(this,PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}
