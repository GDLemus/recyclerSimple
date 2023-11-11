package com.example.practicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class registrerActivity extends AppCompatActivity {

    TextView backToLogin;
    EditText nameEditText, emailEditText, passEditText, confirmEditText;
    Button singUpBoton;

    userDAO dao = new userDAO();

    Spinner generoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        nameEditText = findViewById(R.id.nameEditTextText);
        emailEditText = findViewById(R.id.emailEditTextText);
        passEditText = findViewById(R.id.passwordEditTextText);
        confirmEditText = findViewById(R.id.confirmPassEditTextText);

        generoSpinner = findViewById(R.id.generoSpinner);

        List<String> item = new ArrayList<>();
        item.add("Masculino");
        item.add("Femenino");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        generoSpinner.setAdapter(adapter);

        generoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionarOpcion = item.get(position);
                Toast.makeText(getApplicationContext(), "S"+ seleccionarOpcion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        singUpBoton = findViewById(R.id.registerButton);
        singUpBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


        backToLogin = findViewById(R.id.backLoginTextView);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registrerActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void validate(){

        String email = emailEditText.getText().toString().trim();
        String pass = passEditText.getText().toString().trim();
        String confirmPass = confirmEditText.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Correo invalido");
            return;
        } else {
            emailEditText.setError(null);
        }

        if (!confirmPass.equals(pass)){
            confirmEditText.setError("Deben ser iguales");
            return;
        } else {
            confirmEditText.setError(null);
        }

        singUp();
    }

    public void singUp(){
        String genero = generoSpinner.getSelectedItem().toString();
        userModel usu = new userModel(nameEditText.getText().toString(), emailEditText.getText().toString(),passEditText.getText().toString());
        usu.setUserGenero(genero);
        dao.createUser(usu).addOnSuccessListener(suc->
        {
            Intent intent = new Intent(registrerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(registrerActivity.this, "Registrado!", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er->
        {
            Toast.makeText(registrerActivity.this, "Fallo al registrar", Toast.LENGTH_SHORT).show();
        });
    }
}