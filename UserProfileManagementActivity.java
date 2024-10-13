package com.example.dogfoodapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileManagementActivity extends AppCompatActivity {
    private EditText addressEditText, paymentMethodEditText;
    private Button saveButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_management);

        addressEditText = findViewById(R.id.addressEditText);
        paymentMethodEditText = findViewById(R.id.paymentMethodEditText);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        int userId = getIntent().getIntExtra("USER_ID", -1);
        if (userId != -1) {
            UserDAO userDAO = DatabaseHelper.getInstance(this).getUserDAO();
            new Thread(() -> {
                User user = userDAO.getUserById(userId);
                runOnUiThread(() -> {
                    if (user != null) {
                        addressEditText.setText(user.getAddress());
                        paymentMethodEditText.setText(user.getPaymentMethod());
                    }
                });
            }).start();
        }

        saveButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString();
            String paymentMethod = paymentMethodEditText.getText().toString();

            if (!address.isEmpty() && !paymentMethod.isEmpty()) {
                UserDAO userDAO = DatabaseHelper.getInstance(UserProfileManagementActivity.this).getUserDAO();
                new Thread(() -> {
                    User user = userDAO.getUserById(userId);
                    if (user != null) {
                        user.setAddress(address);
                        user.setPaymentMethod(paymentMethod);
                        userDAO.update(user);
                        runOnUiThread(() -> Toast.makeText(UserProfileManagementActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show());
                    } else {
                        runOnUiThread(() -> Toast.makeText(UserProfileManagementActivity.this, "User not found", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                Toast.makeText(UserProfileManagementActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
        backButton.setOnClickListener(v -> finish());
    }
}