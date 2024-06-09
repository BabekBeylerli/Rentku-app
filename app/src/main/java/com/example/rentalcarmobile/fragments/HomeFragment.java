package com.example.rentalcarmobile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.adapter.RentalCarAdapter;
import com.example.rentalcarmobile.dao.database.RentalCarDatabase;
import com.example.rentalcarmobile.dao.entity.RentalCar;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private RentalCarDatabase db;
    private RecyclerView recyclerView;
    private RentalCarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = Room.databaseBuilder(getContext(), RentalCarDatabase.class, "rental-car-database")
                .allowMainThreadQueries()
                .build();

        recyclerView = view.findViewById(R.id.mRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadRentalCars();

        return view;
    }

    private void loadRentalCars() {
        List<RentalCar> rentalCarList = db.rentalCarDao().getAllRentalCars();
        adapter = new RentalCarAdapter(rentalCarList);
        recyclerView.setAdapter(adapter);
    }
}
