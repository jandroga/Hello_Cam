package com.example.hellocam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int camperm = 1;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private Button button;
    String currentPhotoPath;
    private int codiFoto = 100;
    private TextView textView;

/*    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_de_prova" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  *//* prefix *//*
                ".jpg",         *//* suffix *//*
                storageDir      *//* directory *//*
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }*/

/*    private void intentDeFoto(){
        Intent ferUnaFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Action_image_capture és molt descriptiu
        if (ferUnaFoto.resolveActivity(getPackageManager()) != null){
            startActivityForResult(ferUnaFoto, camperm);
        }
    }*/

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == camperm && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView3 = findViewById(R.id.imageViewTocha3);
        imageView2 = findViewById(R.id.imageViewTocha2);
        imageView = findViewById(R.id.imageViewTocha);//afegim l'imatgeview del layout
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView3);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, codiFoto);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, codiFoto);

            }
        });

       //button2.setOnClickListener(new View.OnClickListener()

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            textView.setVisibility(View.GONE);
        }
        if(requestCode == 101){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView2.setImageBitmap(bitmap);
        }
        if(requestCode == 102){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView3.setImageBitmap(bitmap);
            textView.setVisibility(View.VISIBLE);
        }codiFoto++;
        if(codiFoto > 102){
            codiFoto = 100;
        }
    }


}