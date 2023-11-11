package com.example.practicaapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class userDAO {

    private DatabaseReference databaseReference;

    public userDAO(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(userModel.class.getSimpleName());
    }

    public Task<Void> createUser(userModel user){
        return databaseReference.push().setValue(user);
    }

    public Task<Void> consultaLogin(final String userEmail, final String userPass){

        final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        databaseReference.orderByChild("userEmail").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Variable que verifica si se encontro el usuario
                boolean userFound = false;

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    userModel user = userSnapshot.getValue(userModel.class);

                    if (user != null && user.getUserPass().equals(userPass)) {
                        // credenciales validas
                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    Log.d("LoginError", "Usuario mno encontrado o credenciales invalidas");
                }

                taskCompletionSource.setResult(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                taskCompletionSource.setException(error.toException());
            }
        });
        return taskCompletionSource.getTask();
    }


    public Task<Boolean> usuarioHaIniciadoSesion(final String userEmail){
        final TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();
        Query query = databaseReference.orderByChild("userEmail").equalTo(userEmail);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean userEmailFound = false;

                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    userModel usu = itemSnapshot.getValue(userModel.class);

                    if (usu != null){
                        userEmailFound = true;
                        break;
                    }
                }

                taskCompletionSource.setResult(userEmailFound);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                taskCompletionSource.setException(error.toException());
            }
        });
        return taskCompletionSource.getTask();
    }

}
