package com.example.tara.NavMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tara.Models.ExploreModel;
import com.example.tara.Adapter.ExploreModelAdapter;
import com.example.tara.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class ExploreFragment extends Fragment {

    RecyclerView recyclerView;
    ExploreModelAdapter mainExploreModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

    recyclerView = (RecyclerView)view.findViewById(R.id.carListRV);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ExploreModel> options =
                new FirebaseRecyclerOptions.Builder<ExploreModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("car"), ExploreModel.class)
                        .build();


        mainExploreModel = new ExploreModelAdapter(options);
        recyclerView.setAdapter(mainExploreModel);
        return view;

    }


    }

