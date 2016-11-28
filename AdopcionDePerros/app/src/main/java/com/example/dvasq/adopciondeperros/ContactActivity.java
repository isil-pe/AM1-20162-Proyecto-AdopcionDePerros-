package com.example.dvasq.adopciondeperros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {
    TextView tviNom, tviTele, tviEmail;
    UsuarioEntity usuarioEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        extras();
        app();
    }

    private void app(){
        ui();

        if(usuarioEntity!=null){
            tviNom.setText(usuarioEntity.getName());
            tviTele.setText(usuarioEntity.getPhone());
            tviEmail.setText(usuarioEntity.getEmail());
        }
    }

    private void ui(){
        tviNom = (TextView)findViewById(R.id.tviConNom);
        tviTele = (TextView)findViewById(R.id.tviConTele);
        tviEmail = (TextView)findViewById(R.id.tviConEmail);
    }

    private void extras(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            usuarioEntity = (UsuarioEntity) bundle.getSerializable("User");
        }
    }
}
