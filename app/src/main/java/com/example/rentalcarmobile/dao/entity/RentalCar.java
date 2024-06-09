package com.example.rentalcarmobile.dao.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rental_cars")
public class RentalCar {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String model;
    public String year;
    public String km;
    public String price;
    public String photoPath; // New field for photo path

    // Getters and setters or public fields can be used
}
