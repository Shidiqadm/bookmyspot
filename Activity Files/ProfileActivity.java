package com.example.newpark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    EditText editname,email,vehicleid,pass;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.modname);
        editname = findViewById(R.id.edtName);
        email = findViewById(R.id.edtMail);
        vehicleid = findViewById(R.id.edtVehId);
        update = findViewById(R.id.book);
        pass = findViewById(R.id.edtPass);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                editname.setText(documentSnapshot.getString("Name"));
                name.setText(documentSnapshot.getString("Name"));
                email.setText(documentSnapshot.getString("email"));
                //pass.setText(documentSnapshot.getString("Pass"));
                vehicleid.setText(documentSnapshot.getString("vehID"));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newName = editname.getText().toString();
                String newemail = email.getText().toString();
                String  newVehid = vehicleid.getText().toString();
                //String newPass = pass.getText().toString();




                DocumentReference documentReference = fStore.collection("users").document(userId);
                Map<String,Object> user = new HashMap<>();
                user.put("Name", newName);
                user.put("email", newemail);
                user.put("vehID", newVehid);
                //user.put("Pass"), newPass);
                documentReference.set(user);

                startActivity(new Intent(ProfileActivity.this,DashboardActivity.class));

            }
        });
    }
}