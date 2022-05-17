package com.example.tara.Explore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tara.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class   CarDetails extends AppCompatActivity {
    DatabaseReference database;
    TextView idTV,tvAddress1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        idTV = findViewById(R.id.tvId);
        tvAddress1 = findViewById(R.id.tvAddress1);

        String databaseLocation = getString(R.string.databasePath);

        database = FirebaseDatabase.getInstance(databaseLocation).getReference("vehicle");


//test test

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String id = getIntent().getStringExtra("userId");
                idTV.setText(id);
                String address1 = getIntent().getStringExtra("address1");
                tvAddress1.setText(address1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}