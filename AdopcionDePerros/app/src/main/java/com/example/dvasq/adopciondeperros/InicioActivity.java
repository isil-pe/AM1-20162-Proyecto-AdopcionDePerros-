package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnRegistrar = (Button)findViewById(R.id.btnregistrar);

        btnLogin.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
    }

    private void gotoMain(){
        Intent intent = new Intent(InicioActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoRegis(){
        Intent intent = new Intent(InicioActivity.this,RegistrarActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                gotoMain();
                break;
            case R.id.btnregistrar:
                gotoRegis();
                break;
        }
    }
}
