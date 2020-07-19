package com.example.newpark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class QrActivity extends AppCompatActivity {

    ImageView qrcode;
    Button back;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView source;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        qrcode.findViewById(R.id.qr);
        back.findViewById(R.id.back_1);
        source.findViewById(R.id.id);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                source.setText(documentSnapshot.getString("Spotno"));
            }
        });

    }
}