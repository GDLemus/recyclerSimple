package com.example.practicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class updateActivity extends AppCompatActivity {


    EditText tituloEdit, descripcionEdit, fechaEdit;

    Button editarBoton;

    ImageView backImaqeView;

    String key;
    taskDAO daoTks = new taskDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tituloEdit = findViewById(R.id.titleEditTextTextEdit);
        descripcionEdit = findViewById(R.id.detailEditTextTextEdit);
        fechaEdit = findViewById(R.id.dateEditTextTextEdit);

        String titulo = getIntent().getStringExtra("titulo");
        String descripcion = getIntent().getStringExtra("descripcion");
        String fecha = getIntent().getStringExtra("fecha");
        key = getIntent().getStringExtra("key");
        tituloEdit.setText(titulo);
        descripcionEdit.setText(descripcion);
        fechaEdit.setText(fecha);

        editarBoton = findViewById(R.id.editarButton);
        editarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nuevoTitulo = tituloEdit.getText().toString();
                String nuevaDescripcion = descripcionEdit.getText().toString();
                String nuevaFecha = fechaEdit.getText().toString();

                HashMap hashMap = new HashMap<>();
                hashMap.put("titulo", nuevoTitulo);
                hashMap.put("descripcio", nuevaDescripcion);
                hashMap.put("fecha", nuevaFecha);
                daoTks.editarTarea("key", hashMap).addOnSuccessListener(suc->{
                    Toast.makeText(updateActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(updateActivity.this, detailActivity.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(updateActivity.this, "Fallo al editar", Toast.LENGTH_SHORT).show();
                });
            }
        });


        backImaqeView = findViewById(R.id.backImageViewEdit);
        backImaqeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateActivity.this, detailActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}