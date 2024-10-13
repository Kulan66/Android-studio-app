package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button manageUsersButton = findViewById(R.id.buttonManageUsers);
        Button manageDogFoodButton = findViewById(R.id.buttonManageDogFood);
        Button logoutButton = findViewById(R.id.buttonLogout);

        manageUsersButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ManageUsersActivity.class);
            startActivity(intent);
        });

        manageDogFoodButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ManageDogFoodActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close the admin activity
        });
    }
}
