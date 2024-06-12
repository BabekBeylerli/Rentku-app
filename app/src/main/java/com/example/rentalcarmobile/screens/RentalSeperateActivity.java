package com.example.rentalcarmobile.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.dao.database.RentalCarDatabase;
import com.example.rentalcarmobile.dao.entity.RentalCar;

public class RentalSeperateActivity extends AppCompatActivity {

    private RentalCarDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_seperate);

        db = Room.databaseBuilder(getApplicationContext(), RentalCarDatabase.class, "rental-car-database")
                .allowMainThreadQueries()
                .build();

        int carId = getIntent().getIntExtra("CarId", -1);
        if (carId != -1) {
            RentalCar rentalCar = db.rentalCarDao().getRentalCarById(carId);
            if (rentalCar != null) {
                setCarDetails(rentalCar);
            }
        }
    }

    private void setCarDetails(RentalCar rentalCar) {
        ImageView rentalPhoto = findViewById(R.id.imageView);
        TextView carNameTextView = findViewById(R.id.carName);
        //  TextView carModelTextView = findViewById(R.id.carModel);
        TextView carYearTextView = findViewById(R.id.carYear);
        TextView carKmTextView = findViewById(R.id.carKm);
        TextView carPriceTextView = findViewById(R.id.carPrice);
        TextView emailTextView = findViewById(R.id.contactAdress);

        if (rentalCar.photoPath != null) {
            Uri photoUri = Uri.parse(rentalCar.photoPath);
            rentalPhoto.setImageURI(photoUri);
        }

        carNameTextView.setText(rentalCar.name);
      //  carModelTextView.setText(rentalCar.model);
        carYearTextView.setText(rentalCar.year);
        carKmTextView.setText(rentalCar.km);
        carPriceTextView.setText(rentalCar.price);
        emailTextView.setText("contact@example.com"); // You can get the email from rentalCar if available
    }
}
