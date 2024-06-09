package com.example.rentalcarmobile.dao.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rentalcarmobile.dao.entity.RentalCar;
import com.example.rentalcarmobile.dao.interfac.RentalCarDao;

@Database(entities = {RentalCar.class}, version = 1)
public abstract class RentalCarDatabase extends RoomDatabase {
    public abstract RentalCarDao rentalCarDao();
}

