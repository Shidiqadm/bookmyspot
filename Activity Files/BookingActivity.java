package com.example.newpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    TextView error, total;
    EditText vehid, spot, hours;
    Button book;
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

                    }

                    total.setText("₹"+Double.toString(calculateTotal()));
                }

            }
        });


    }
    private double calculateTotal(){
        double i = Double.parseDouble(hours.getText().toString()) * 4.5;
        return i;
    }
}