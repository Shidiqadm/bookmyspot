package com.example.newpark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    TextView name,vehid,spot,hours,total;
    Button back,qrcode;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.detail_name);
        vehid = findViewById(R.id.detail_vehid);
        spot = findViewById(R.id.detail_spot);
        hours = findViewById(R.id.detail_hours);
        total = findViewById(R.id.detail_total);
        back = findViewById(R.id.back);
        qrcode = findViewById(R.id.qrcode);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;

                name.setText(documentSnapshot.getString("Name"));
                vehid.setText(documentSnapshot.getString("vehID"));
                spot.setText(documentSnapshot.getString("Spotno"));
                hours.setText(documentSnapshot.getString("Hours"));
                total.setText("₹"+documentSnapshot.getString("Total"));
            }
        });


//        qrcode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent qrAct = new Intent(DetailsActivity.this,QrActivity.class);
//                startActivity(qrAct);
//                finish();
//            }
//        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToDash = new Intent(DetailsActivity.this,DashboardActivity.class);
                startActivity(backToDash);
                finish();
            }
        });


    }
}