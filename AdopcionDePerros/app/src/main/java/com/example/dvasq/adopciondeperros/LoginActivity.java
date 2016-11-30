package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

public class LoginActivity extends AppCompatActivity {
    private EditText eteUser, etePass;
    UsuarioEntity usuario;
    private Button btningresar;
    private Button btnCancelar;
    private String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ui();
        events();
    }

    private void ui(){
        btningresar= (Button)findViewById(R.id.btningresar);
        btnCancelar = (Button)findViewById(R.id.btncancelar);

        eteUser = (EditText)findViewById(R.id.etxuser);
        etePass = (EditText)findViewById(R.id.etxpass);
    }

    private void events(){
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar()){
                    PerroApplication application = (PerroApplication)getApplication();
                    application.setCurrentUser(usuario);
                    gotoMain();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Revisa usuario y contraseña",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validar(){
        user = eteUser.getText().toString().trim();
        pass = etePass.getText().toString().trim();
        PerroApplication application = (PerroApplication)getApplication();

        if(user.isEmpty()){
            eteUser.setError("Obligatorio");
            return false;
        }
        if(pass.isEmpty()){
            etePass.setError("Obligatorio");
            return false;
        }

        usuario = application.validar(user,pass);

        if(usuario==null){
            return false;
        }



        return true;
    }


    private void gotoMain(){
        Intent intent = new Intent(this,PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}
