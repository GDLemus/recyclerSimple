package com.example.practicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class detailActivity extends AppCompatActivity {

    FloatingActionButton opcionesFab, editarFab, eliminarFab;
    ImageView backImageView;

    TextView tituloView, detalleView, fechaView;
    boolean opcionesVisible = false;

    taskDAO daoTks = new taskDAO();

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tituloView = findViewById(R.id.tituloTextView);
        detalleView = findViewById(R.id.detailTextView);
        fechaView = findViewById(R.id.fechaTextView);


        String titulo = getIntent().getStringExtra("titulo");
        String descripcion = getIntent().getStringExtra("descripcion");
        String fecha = getIntent().getStringExtra("fecha");
        key = getIntent().getStringExtra("key");
        tituloView.setText(titulo);
        detalleView.setText(descripcion);
        fechaView.setText(fecha);


        opcionesFab = findViewById(R.id.optionsFab);
        opcionesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               consultaFab();
            }
        });

        editarFab = findViewById(R.id.editFab);
        editarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailActivity.this, updateActivity.class);
                startActivity(intent);
                finish();
            }
        });
        eliminarFab = findViewById(R.id.deleteFab);
        eliminarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoTks.eliminarTarea(key).addOnSuccessListener(suc->
                {
                    Toast.makeText(detailActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(detailActivity.this, createActivity.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(detailActivity.this, "Fallo al eliminar", Toast.LENGTH_SHORT).show();
                });
            }
        });




    backImageView = findViewById(R.id.imageView2);
    backImageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(detailActivity.this, createActivity.class);
            startActivity(intent);
            finish();
        }
    });
    }


    public void consultaFab(){
        opcionesVisible = !opcionesVisible;

        if (opcionesVisible){
            editarFab.setVisibility(View.VISIBLE);
            eliminarFab.setVisibility(View.VISIBLE);
        } else {
            editarFab.setVisibility(View.GONE);
            eliminarFab.setVisibility(View.GONE);
        }
    }
}