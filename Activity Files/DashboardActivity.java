package com.example.newpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {


    ImageView profileBtn,bookingBtn,detailsBtn,signoutBtn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileBtn = findViewById(R.id.imageView4);
        bookingBtn = findViewById(R.id.imageView5);
        detailsBtn = findViewById(R.id.imageView);
        signoutBtn = findViewById(R.id.imageView3);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intprofile = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(intprofile);
            }
        });

        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intbooking = new Intent(DashboardActivity.this,BookingActivity.class);
                startActivity(intbooking);
            }
        });

        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intdetails = new Intent(DashboardActivity.this,DetailsActivity.class);
                startActivity(intdetails);
            }
        });

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(DashboardActivity.this ,LoginActivity.class);
                startActivity(intToMain);
                finish();
            }
        });





    }
}