package com.example.newpark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BookingActivity extends AppCompatActivity {

    TextView error, total;
    EditText vehid, spot, hours;
    Button book;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    List<String> spots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        error = findViewById(R.id.error);
        total = findViewById(R.id.total);
        vehid = findViewById(R.id.vehId);
        spot = findViewById(R.id.spot);
        hours = findViewById(R.id.hours);
        book = findViewById(R.id.book);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                vehid.setText(documentSnapshot.getString("vehID"));
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vehid.getText().toString().isEmpty() || spot.getText().toString().isEmpty() || hours.getText().toString().isEmpty()) {

                    error.setText("* Fill in all the fields");
                }
                else{

                    if(spots.contains(spot.getText().toString())){

                        error.setText("* Spot not available at this time!");
                    }
                    else{
                        spots.add(spot.getText().toString());
                        Toast.makeText(BookingActivity.this,"Booking Confirmed",Toast.LENGTH_SHORT).show();


                        //startActivity(new Intent(BookingActivity.this,DashboardActivity.class));

                        DocumentReference documentReference = fStore.collection("users").document(userId);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Spotno", spot.getText().toString());
                        user.put("Hours", hours.getText().toString());
                        user.put("Total", Double.toString(calculateTotal()));

                        documentReference.set(user, SetOptions.merge());

                    }

                    total.setText("₹"+Double.toString(calculateTotal()));
                }

            }
        });


    }
    protected double calculateTotal(){
        double i = Double.parseDouble(hours.getText().toString()) * 12.5;
        return i;
    }
}