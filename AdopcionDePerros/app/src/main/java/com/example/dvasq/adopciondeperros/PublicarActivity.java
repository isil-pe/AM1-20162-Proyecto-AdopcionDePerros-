package com.example.dvasq.adopciondeperros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dvasq.adopciondeperros.model.PerroEntity;

/**
 * Created by patty on 27/11/2016.
 */

public class PublicarActivity extends AppCompatActivity {
    private Spinner spraza,spweight;
    private EditText eteName,eteAge,etePeso,eteVacuna;

    private Button btnSignUp;

    private String raza=null;
    private String weight=null;
    private int edad=0;
    private String name=null;
    private String peso=null;
    private String vacunas=null;
    private int id=0;

    @Override
    protected  void onCreate(Bundle savedInstancesState)
    {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_publicar);
        app();

    }

    private void app() {
        eteName=(EditText)findViewById(R.id.eteName);
        eteAge=(EditText)findViewById(R.id.eteAge);
        etePeso=(EditText)findViewById(R.id.etePeso);
        eteVacuna=(EditText)findViewById(R.id.etevacunas);
        spraza=(Spinner)findViewById(R.id.race);
        spweight=(Spinner)findViewById(R.id.weight);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        events();
    }

    private void events()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    addPerro();
                    gotoPrincipal();
                } else {
                    Toast.makeText(PublicarActivity.this, "Revisar campos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        spraza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

         {
             @Override
             public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){
                 Log.v("CONSOLE", "spraza" + adapterView.getAdapter().getItem(i));
                 raza = adapterView.getAdapter().getItem(i).toString();
             }

             @Override
             public void onNothingSelected (AdapterView < ? > adapterView){

             }
         }

        );

        spweight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("CONSOLE", "sptamaño" + adapterView.getAdapter().getItem(i));
                weight = adapterView.getAdapter().getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

    private boolean validateForm(){
        String name=eteName.getText().toString();
        String age=eteAge.getText().toString();
        String peso=etePeso.getText().toString();
        String vacuna=eteVacuna.getText().toString();

        if(name.isEmpty())return false;
        if(age.isEmpty())return false;
        if(peso.isEmpty())return false;
        if(vacuna.isEmpty())return false;

        return true;
    }

    private void gotoPrincipal(){
        finish();
    }

    private void addPerro(){
        PerroApplication aplicacion = (PerroApplication) getApplication();

        name = eteName.getText().toString().trim();
        weight=spweight.getSelectedItem().toString().trim();
        edad= Integer.parseInt(eteAge.getText().toString().trim());
        raza=spraza.getSelectedItem().toString().trim();
        peso=etePeso.getText().toString().trim();
        vacunas=eteVacuna.getText().toString().trim();

        id = aplicacion.countPerros();

        if(id!= 0){
            id += 1;
        }

        PerroEntity perrito = new PerroEntity(id, name, raza, "", edad, weight, "", 1,1);
        aplicacion.addPerro(perrito);


    }

}


