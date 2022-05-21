package com.example.tara.Bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tara.Models.Booking;
import com.example.tara.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class BookingsFragmentMenu extends Fragment {
    RecyclerView recyclerview;
    DatabaseReference databaseReference;
    ArrayList<Booking> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        recyclerview = view.findViewById(R.id.bookingsRV);
        String databaseLocation = getString(R.string.databasePath);
        databaseReference= FirebaseDatabase.getInstance(databaseLocation).getReference("vehicle");
        list = new ArrayList<>();




        return view;
    }
}