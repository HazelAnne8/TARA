package com.example.tara.Host;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tara.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//displays all the users host cars
public class HostedCars extends Fragment {
//buko juice
    String databaseLocation;
    FirebaseAuth mAuth;
    TextView tvBrandModelYear, tvPlateNumber;
    ImageView ivCarImage;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hosted_cars, container, false);
        FloatingActionButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), HostCar.class));
            }
        });
        tvBrandModelYear = view.findViewById(R.id.tvBrandModelYear);
        tvPlateNumber = view.findViewById(R.id.tvPlateNumber);
        ivCarImage = view.findViewById(R.id.ivCarImage);

        //connect to the database to fetch data
        databaseLocation = getString(R.string.databasePath);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance(databaseLocation).getReference("vehicle").child(userId);

        //fetch data from realtime database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            //fetches data from database and displays it
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageUrl = snapshot.child("carUrl").getValue().toString();
                String model = snapshot.child("model").getValue().toString();
                String brand = snapshot.child("brand").getValue().toString();
                String year = snapshot.child("year").getValue().toString();
                String plateNumber = snapshot.child("plateNumber").getValue().toString();

               // Glide.with(HostedCars.this).load(Uri.parse(imageUrl)).into(ivCarImage);
                tvBrandModelYear.setText(model+" "+brand+" "+year);
                tvPlateNumber.setText(plateNumber);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }


}
