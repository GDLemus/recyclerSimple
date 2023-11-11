package com.example.practicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class uploadActivity extends AppCompatActivity {

    EditText tituloEdittext, detalleEdittext, fechaEdittext;

    Button insertarBoton;

    ImageView regresarBoton;

    taskDAO dao = new taskDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        tituloEdittext = findViewById(R.id.titleEditTextText);
        detalleEdittext = findViewById(R.id.detailEditTextText);
        fechaEdittext = findViewById(R.id.dateEditTextText);

        insertarBoton = findViewById(R.id.createButton);
        insertarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tasksModel tks = new tasksModel(tituloEdittext.getText().toString(), detalleEdittext.getText().toString(), fechaEdittext.getText().toString());
                dao.crearTarea(tks).addOnSuccessListener(er->{
                    Intent intent = new Intent(uploadActivity.this, createActivity.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(uploadActivity.this, "Fallo al crear", Toast.LENGTH_SHORT).show();
                });
            }
        });

    regresarBoton = findViewById(R.id.backImageView);
    regresarBoton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(uploadActivity.this, createActivity.class);
            startActivity(intent);
            finish();
        }
    });

    }
}