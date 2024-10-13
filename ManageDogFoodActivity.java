package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageDogFoodActivity extends AppCompatActivity {

    private EditText etFoodName, etFoodPrice, etFoodId;
    private Button btnAddFood, btnEditFood, btnRemoveFood, btnBack;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_dog_food);

        // Initialize views
        etFoodName = findViewById(R.id.etFoodName);
        etFoodPrice = findViewById(R.id.etFoodPrice);
        etFoodId = findViewById(R.id.etFoodId);
        btnAddFood = findViewById(R.id.btnAddFood);
        btnEditFood = findViewById(R.id.btnEditFood);
        btnRemoveFood = findViewById(R.id.btnRemoveFood);
        btnBack = findViewById(R.id.btnBack);

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper.getInstance(this);

        // Set onClick listeners
        btnAddFood.setOnClickListener(v -> addDogFood());
        btnEditFood.setOnClickListener(v -> editDogFood());
        btnRemoveFood.setOnClickListener(v -> removeDogFood());
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ManageDogFoodActivity.this, AdminActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void addDogFood() {
        String name = etFoodName.getText().toString();
        String priceStr = etFoodPrice.getText().toString();
        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please enter food name and price", Toast.LENGTH_SHORT).show();
            return;
        }
        double price = Double.parseDouble(priceStr);
        DogFood dogFood = new DogFood();
        dogFood.setName(name);
        dogFood.setPrice(price);
        new Thread(() -> {
            databaseHelper.getDogFoodDAO().insert(dogFood);
            runOnUiThread(() -> Toast.makeText(this, "Dog food added successfully", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void editDogFood() {
        int foodId = Integer.parseInt(etFoodId.getText().toString());
        String newName = etFoodName.getText().toString();
        String newPriceStr = etFoodPrice.getText().toString();
        new Thread(() -> {
            DogFood dogFood = databaseHelper.getDogFoodDAO().getDogFoodById(foodId);
            if (dogFood != null) {
                dogFood.setName(newName);
                dogFood.setPrice(Double.parseDouble(newPriceStr));
                databaseHelper.getDogFoodDAO().update(dogFood);
                runOnUiThread(() -> Toast.makeText(this, "Dog food updated successfully", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(this, "Dog food not found", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void removeDogFood() {
        int foodId = Integer.parseInt(etFoodId.getText().toString());
        new Thread(() -> {
            DogFood dogFood = databaseHelper.getDogFoodDAO().getDogFoodById(foodId);
            if (dogFood != null) {
                databaseHelper.getDogFoodDAO().delete(dogFood);
                runOnUiThread(() -> Toast.makeText(this, "Dog food removed successfully", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(this, "Dog food not found", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

}
