package com.example.dvasq.adopciondeperros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dvasq.adopciondeperros.model.UsuarioEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarActivity extends AppCompatActivity {
    EditText etxNom, etxApe, etxTele, etxEmail, etxUser, etxPass, etxConfirm;
    Button btnGuardar;
    PerroApplication application;
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
                if(validar()) {
                    addUser();
                    gotoPrincipal();
                }else{
                    Toast.makeText(RegistrarActivity.this, "Revisar campos",
                            Toast.LENGTH_SHORT).show();

                }
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

    private boolean validar(){
        application = (PerroApplication)getApplication();
        name = etxNom.getText().toString().trim();
        String ape = etxApe.getText().toString().trim();
        phone = etxTele.getText().toString().trim();
        email = etxEmail.getText().toString().trim();
        username = etxUser.getText().toString().trim();
        password = etxPass.getText().toString().trim();
        String confirm = etxConfirm.getText().toString().trim();

        if(name.isEmpty()){
            etxNom.setError("Obligatorio");
            return false;
        }
        if(ape.isEmpty()){
            etxApe.setError("Obligatorio");
            return false;
        }
        if(phone.isEmpty()){
            etxTele.setError("Obligatorio");
            return false;
        }
        if(email.isEmpty()){
            etxEmail.setError("Obligatorio");
            return false;
        }
        if(username.isEmpty()){
            etxUser.setError("Obligatorio");
            return false;
        }
        if(password.isEmpty()){
            etxPass.setError("Obligatorio");
            return false;
        }
        if(confirm.isEmpty()){
            etxConfirm.setError("Obligatorio");
            return false;
        }

        if(!password.equals(confirm)){
            etxPass.setError("Contraseñas no son el mismo");
            return false;
        }

        if(!isEmailValid(email)){
            etxEmail.setError("Email Invalido");
            return false;
        }

        if(!application.validarUsuario(username)){
            etxUser.setError("Usuario ya existe");
            return false;
        }

        return true;
    }

    private void addUser(){
        application = (PerroApplication)getApplication();

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

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}
