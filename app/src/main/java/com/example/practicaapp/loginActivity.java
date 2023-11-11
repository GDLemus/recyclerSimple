package com.example.practicaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class loginActivity extends AppCompatActivity {

    TextView goToSignUp ,errorTextView;
    EditText emailEditText, passwordEditText;
    Button sesionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditTextText);
        passwordEditText = findViewById(R.id.passwordEditTextText);

        sesionButton = findViewById(R.id.sesionButton);
        sesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateLogin();
            }
        });



        goToSignUp = findViewById(R.id.goToSignUpTextView);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, registrerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        errorTextView = findViewById(R.id.errorTextView);


    }


    public void validateLogin(){

        String email = emailEditText.getText().toString().trim();
        String pass = passwordEditText.getText().toString().trim();


        if (email.isEmpty() || pass.isEmpty()){
            errorTextView.setText("Debes llenar todos los campos!");
            return;
        } else {
            errorTextView.setError(null);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Correo invalido");
            return;
        } else {
            emailEditText.setError(null);
        }

        if (pass.isEmpty() || pass.length() <8){
            passwordEditText.setError("Deben ser almenos 8 caracteres");
            return;
        } else {
            passwordEditText.setError(null);
        }



        iniciarLogin();
    }


    public void iniciarLogin(){

        userDAO dao = new userDAO();
        dao.consultaLogin(emailEditText.getText().toString(), passwordEditText.getText().toString()).addOnSuccessListener(suc->
        {
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }).addOnFailureListener(er->
        {
            Toast.makeText(loginActivity.this, "Error al registrarse verifica las credenciales",  Toast.LENGTH_SHORT).show();
        });
    }
}