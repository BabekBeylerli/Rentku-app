package com.example.rentalcarmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.dao.entity.RentalCar;
import com.example.rentalcarmobile.screens.RentalSeperateActivity;

import java.util.List;

public class RentalCarAdapter extends RecyclerView.Adapter<RentalCarAdapter.ViewHolder> {

    private List<RentalCar> rentalCarList;
    private Context context;

    public RentalCarAdapter(Context context, List<RentalCar> rentalCarList) {
        this.context = context;
        this.rentalCarList = rentalCarList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RentalCar rentalCar = rentalCarList.get(position);
        holder.rentalName.setText(rentalCar.name);
        holder.rentalModel.setText(rentalCar.model);
        holder.rentalYear.setText(rentalCar.year);
        holder.rentalKm.setText(rentalCar.km);
        holder.rentalPrice.setText(rentalCar.price);

        if (rentalCar.photoPath != null) {
            Uri photoUri = Uri.parse(rentalCar.photoPath);
            holder.rentalPhoto.setImageURI(photoUri);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RentalSeperateActivity.class);
            intent.putExtra("CarId", rentalCar.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rentalCarList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rentalName;
        public TextView rentalModel;
        public TextView rentalYear;
        public TextView rentalKm;
        public TextView rentalPrice;
        public ImageView rentalPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            rentalName = itemView.findViewById(R.id.rentalName);
            rentalModel = itemView.findViewById(R.id.rentalModel);
            rentalYear = itemView.findViewById(R.id.rentalYear);
            rentalKm = itemView.findViewById(R.id.rentalKm);
            rentalPrice = itemView.findViewById(R.id.rentalPrice);
            rentalPhoto = itemView.findViewById(R.id.rentalPhoto);
        }
    }
}
