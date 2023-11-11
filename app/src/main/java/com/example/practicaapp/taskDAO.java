package com.example.practicaapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class taskDAO {

    private DatabaseReference databaseReference;

    public taskDAO(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(tasksModel.class.getSimpleName());
    }

    public Task<Void> crearTarea(tasksModel tasks){
        return databaseReference.push().setValue(tasks);
    }

    public Task<Void> editarTarea(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> eliminarTarea(String key){
        return databaseReference.child(key).removeValue();
    }
}
