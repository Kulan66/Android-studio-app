package com.example.dogfoodapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, DogFood.class, CartItem.class,Admin.class}, version = 1)
public abstract class DatabaseHelper extends  RoomDatabase{

    private static DatabaseHelper instance;

    public abstract UserDAO getUserDAO();
    public abstract DogFoodDAO getDogFoodDAO();
    public abstract CartDAO getCartDAO();
    public abstract AdminDAO getAdminDAO();

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, "dog_food_app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}


