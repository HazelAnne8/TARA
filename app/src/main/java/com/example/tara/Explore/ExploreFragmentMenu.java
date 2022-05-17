package com.example.tara.Explore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tara.Main.RecyclerViewInterface;
import com.example.tara.Models.Car;
import com.example.tara.Adapter.CarAdapter;
import com.example.tara.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.os.Handler;


public class ExploreFragmentMenu extends Fragment implements RecyclerViewInterface {

    RecyclerView recyclerView;
    DatabaseReference database;
    CarAdapter myAdapter;
    ArrayList<Car> list;
    SwipeRefreshLayout swipeRefreshLayout;
    String uId;
    DataSnapshot dataSnapshot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        String databaseLocation = getString(R.string.databasePath);
        recyclerView = view.findViewById(R.id.carListRV);
        database = FirebaseDatabase.getInstance(databaseLocation).getReference("vehicle");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        myAdapter = new CarAdapter(getContext(),list,this);
        recyclerView.setAdapter(myAdapter);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);

        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot = snapshot;
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        Car car = dataSnapshot1.getValue(Car.class);
                        list.add(car);
                    }

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        database.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                        Car car = dataSnapshot1.getValue(Car.class);
                                        list.add(car);
                                    }

                                }
                                myAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });
        return view;
    }

    @Override
    public void onItemClick(int position) {
        int index  = 0;

        for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
            if(index == position){
                DatabaseReference currentReference = childSnapshot.getRef();
                uId = currentReference.getKey();
            }
            index++;
        }

        Intent intent = new Intent(getContext(), CarDetails.class);
        intent.putExtra("userId",uId);
        startActivity(intent);
    }
}

