package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin, btnRegistrar;
    private ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnRegistrar = (Button)findViewById(R.id.btnregistrar);
        imgIcon = (ImageView)findViewById(R.id.imgDogIcon);

        btnLogin.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin();
            }
        });
    }

    private void gotoMain(){
        Intent intent = new Intent(InicioActivity.this,LoginActivity.class);
        startActivity(intent);

    }

    private void gotoRegis(){
        Intent intent = new Intent(InicioActivity.this,RegistrarActivity.class);
        startActivity(intent);
    }

    private void spin(){
        Animation spin = AnimationUtils.loadAnimation(this, R.anim.spin);
        imgIcon.startAnimation(spin);
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
