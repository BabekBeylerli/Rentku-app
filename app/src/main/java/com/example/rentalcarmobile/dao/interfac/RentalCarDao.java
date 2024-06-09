package com.example.rentalcarmobile.dao.interfac;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rentalcarmobile.dao.entity.RentalCar;

import java.util.List;

@Dao
public interface RentalCarDao {
    @Insert
    void insert(RentalCar rentalCar);

    @Query("SELECT * FROM rental_cars")
    List<RentalCar> getAllRentalCars();
}

