package com.example.dogfoodapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DogFoodDAO {

    @Insert
    void insert(DogFood dogFood);

    @Insert
    void insertAll(List<DogFood> dogFoods);

    @Query("SELECT * FROM dog_food WHERE id = :id")
    DogFood getDogFoodById(int id);
    @Query("SELECT * FROM dog_food")
    List<DogFood> getAllDogFood();

    @Update
    void update(DogFood dogFood);

    @Delete
    void delete(DogFood dogFood);

}
