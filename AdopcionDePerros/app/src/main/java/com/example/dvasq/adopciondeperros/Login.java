package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button btnLogin, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnRegistrar = (Button)findViewById(R.id.btnregistrar);


    }

    private void gotoMain(){
        Intent intent = new Intent(Login.this,Ingreso.class);
        startActivity(intent);
        finish();
    }

    private void gotoRegis(){
        Intent intent = new Intent(Login.this,Registrar.class);
        startActivity(intent);
    }
}
