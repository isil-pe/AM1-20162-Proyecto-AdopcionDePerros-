package com.example.dvasq.adopciondeperros;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dvasq.adopciondeperros.model.PerroEntity;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by patty on 27/11/2016.
 */

public class PublicarActivity extends AppCompatActivity {
    private Spinner spraza,spsize;
    private EditText eteName,eteAge;
    private ImageView imageView;

    private Button btnSignUp,btnImage;

    private RadioGroup rbGenero,rbEst;
    private  String genero;
    private  String estado;

    private  String APP_DIRECTORY= "myPictureApp/";
    private  String MEDIA_DIRECTORY =APP_DIRECTORY + "media";


    private final int MY_PERMISSIONS=100;
    private final int PHOTO_CODE=100;
    private  final int SELECT_PICTURE=200;



    private LinearLayout mLlView;
    private  String mPath;

    private String raza=null;
    private String size=null;
    private int age=0;
    private String name=null;

    private PerroEntity perroEntity;
    private  PerroApplication perroApplication;

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
        spraza=(Spinner)findViewById(R.id.race);
        spsize=(Spinner)findViewById(R.id.spsize);

        rbGenero=(RadioGroup)findViewById(R.id.rbGenero);
        rbEst=(RadioGroup)findViewById(R.id.rbEst) ;

        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        imageView=(ImageView)findViewById(R.id.setPicture);
        btnImage=(Button)findViewById(R.id.btnImage);
        mLlView=(LinearLayout)findViewById(R.id.ll_view);

        if (perroEntity !=null)
        {
            eteName.setText(perroEntity.getName());
            eteAge.setText(perroEntity.getAge());
          /*  spraza;
            spsize;
            rbGenero;
            rbGenero;
            imageView;*/

        }
        perroApplication=(PerroApplication)(getApplication());




        if (mayRequestStoragePermission())
            btnImage.setEnabled(true);
        else
            btnImage.setEnabled(false);

        events();
    }

    private void events() {

        rbGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbM:
                        genero = "M";
                        break;
                    case R.id.rbH:
                        genero = "F";
                        break;
                }
            }
        });

        rbEst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbD:
                        estado = "Disponible";
                        break;
                    case R.id.rbA:
                        estado = "Adoptado";
                        break;
                }
            }
        });

        btnImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(PublicarActivity.this);
                builder.setTitle("Elige una opcion");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (options[which] == "Tomar foto") {
                            openCamera();
                        } else if (options[which] == "Elegir de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                        } else if (options[which] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
builder.show();
            }


        });




        btnSignUp.setOnClickListener(new OnClickListener() {
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

        spsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("CONSOLE", "sptamaño" + adapterView.getAdapter().getItem(i));
                size = adapterView.getAdapter().getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



    }
//SUBIR FOTO

    private boolean mayRequestStoragePermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return  true;
        if ((checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&
        (checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED))
        return true;

        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))  )
        {
            Snackbar.make(mLlView,"Los permisos son necesarios", Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok,new OnClickListener(){
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public  void onClick(View v)
                {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA },MY_PERMISSIONS );
                }
            } ).show();
        }
        else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA },MY_PERMISSIONS);
        }

        return  false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode ==MY_PERMISSIONS){
            if (grantResults.length ==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED &&grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(PublicarActivity.this,"Permisos Aceptados",Toast.LENGTH_SHORT).show();
                btnImage.setEnabled(true);
            }
        }
        else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PublicarActivity.this);
        builder.setTitle("PERMISOS DENEGADOS");
        builder.setMessage("para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar",new  DialogInterface.OnClickListener(){
            @Override
            public  void onClick(DialogInterface dialog,int which){
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("packages",getPackageName(),null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }

    private void openCamera() {
        File file=new File(Environment.getExternalStorageDirectory(),MEDIA_DIRECTORY);
        //file.mkdirs();
        boolean isDirectoryCreated=file.exists();

        if (!isDirectoryCreated)
            isDirectoryCreated=file.mkdirs();
        if (isDirectoryCreated){
            Long timestamp = System.currentTimeMillis()/1000;
            String imageName=timestamp.toString()+".jpg";

            mPath=Environment.getExternalStorageDirectory()+ File.separator +MEDIA_DIRECTORY+
                    File.separator+ imageName;

            File newFile=new File(mPath);



            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent,PHOTO_CODE);


        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path",mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath=savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath},null,
                            new MediaScannerConnection.OnScanCompletedListener(){
                                @Override
                                public  void  onScanCompleted(String path,Uri uri){
                                    Log.i("ExternalStorage","Scanned"+path+":");
                                    Log.i("ExternalStorage","->Uri = "+uri);
                                }
                            });

                   /* mPath=Environment.getExternalStorageDirectory()+ File.separator +MEDIA_DIRECTORY+
                            File.separator+ imageName;*/

                    Bitmap bitmap =BitmapFactory.decodeFile(mPath);


                    imageView.setImageBitmap(bitmap);
                    break;
                case  SELECT_PICTURE:
                    Uri path=data.getData();
                    imageView.setImageURI(path);
                    break;

            }
        }

    }

//VALIDACION

    private boolean validateForm(){
        String name=eteName.getText().toString();
        String age=eteAge.getText().toString();


        if(name.isEmpty())return false;
        if(age.isEmpty())return false;



        Log.v("CONSOLE", "genero " + genero);
        if(genero.isEmpty())return false;
        Log.v("CONSOLE", "estado " + estado);
        if(estado.isEmpty())return false;

        return true;
    }

    private void gotoPrincipal(){
        finish();
    }

    private void addPerro(){
        PerroApplication aplicacion = (PerroApplication) getApplication();

        name = eteName.getText().toString().trim();
        size=spsize.getSelectedItem().toString().trim();
        age= Integer.parseInt(eteAge.getText().toString().trim());
        raza=spraza.getSelectedItem().toString().trim();

        id = aplicacion.countPerros();

        if(id!= 0){
            id += 1;
        }

        PerroEntity perrito = new PerroEntity(id, name, raza, genero, age, size, estado, 1,1);
        aplicacion.addPerro(perrito);


    }

}


