package com.example.dogfoodapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AdminDAO {
    @Insert
    void insert(Admin admin);

    @Query("SELECT * FROM admins WHERE email = :email AND password = :password")
    Admin getAdminByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM admins WHERE id = :id")
    Admin getAdminById(int id);

    @Update
    void update(Admin admin);

    @Query("DELETE FROM admins WHERE id = :id")
    void deleteAdminById(int id);
}
