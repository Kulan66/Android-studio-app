package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class UserPageActivity extends AppCompatActivity {

    private Button profileButton, productButton, educationalContentButton,logoutButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        profileButton = findViewById(R.id.profileButton);
        productButton = findViewById(R.id.productButton);
        educationalContentButton = findViewById(R.id.educationalContentButton);
        logoutButton = findViewById(R.id.logoutButton);

        profileButton.setOnClickListener(v -> {
            int userId = getIntent().getIntExtra("USER_ID", -1);
            Intent intent = new Intent(UserPageActivity.this, UserProfileManagementActivity.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        });
        productButton.setOnClickListener(v -> startActivity(new Intent(UserPageActivity.this, DogFoodAndNutritionActivity.class)));
        educationalContentButton.setOnClickListener(v -> startActivity(new Intent(UserPageActivity.this, EducationalContentActivity.class)));
        logoutButton.setOnClickListener(v -> startActivity(new Intent(UserPageActivity.this, MainActivity.class)));
    }

}
