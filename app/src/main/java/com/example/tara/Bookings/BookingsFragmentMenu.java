package com.example.tara.Bookings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tara.Adapter.BookAdapter;
import com.example.tara.Adapter.VehicleAdapter;
import com.example.tara.Main.RecyclerViewInterface;
import com.example.tara.Models.Booking;
import com.example.tara.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BookingsFragmentMenu extends Fragment implements RecyclerViewInterface {
    RecyclerView recyclerview;
    DatabaseReference userRef,vehicleRef;
    ArrayList<Booking> list;
    ArrayList<String> carIdList;
    FirebaseAuth mAuth;
    BookAdapter adapter;
    String userId,carId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        recyclerview = view.findViewById(R.id.bookingsRV);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new BookAdapter(getContext(),list,this);
        recyclerview.setAdapter(adapter);
        String databaseLocation = getString(R.string.databasePath);

        vehicleRef = FirebaseDatabase.getInstance(databaseLocation).getReference("vehicle");
        userRef = FirebaseDatabase.getInstance(databaseLocation).getReference("user").child(userId);

        userRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                DataSnapshot bookedIdSnapshot = userSnapshot.child("bookCarIds");
                for(DataSnapshot userSnapshot2 : bookedIdSnapshot.getChildren()){
                    carIdList.add(userSnapshot2.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


        vehicleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot vehicleSnapshot) {
                for(DataSnapshot vehicleSnapshot2 : vehicleSnapshot.getChildren()){
                    for(DataSnapshot childVehicle : vehicleSnapshot2.getChildren()){
                        String checkId = childVehicle.getKey();
                        if(checkId.equals(carId)){
                            Booking booking = childVehicle.getValue(Booking.class);
                            list.add(booking);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


        return view;
    }

    @Override
    public void onItemClick(int position) {

    }
}