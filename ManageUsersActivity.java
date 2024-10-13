package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUsersActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etUserId;
    private Button btnAddUser, btnEditUser, btnRemoveUser, btnBack;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        // Initialize views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etUserId = findViewById(R.id.etUserId);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnEditUser = findViewById(R.id.btnEditUser);
        btnRemoveUser = findViewById(R.id.btnRemoveUser);
        btnBack = findViewById(R.id.btnBack);

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper.getInstance(this);

        // Set onClick listeners
        btnAddUser.setOnClickListener(v -> addUser());
        btnEditUser.setOnClickListener(v -> editUser());
        btnRemoveUser.setOnClickListener(v -> removeUser());
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ManageUsersActivity.this, AdminActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void addUser(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        new Thread(() -> {
            databaseHelper.getUserDAO().insert(user);
            runOnUiThread(() -> Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void editUser(){
        int userId = Integer.parseInt(etUserId.getText().toString());
        String newEmail = etEmail.getText().toString();
        String newPassword = etPassword.getText().toString();
        new Thread(() -> {
            User user = databaseHelper.getUserDAO().getUserById(userId);
            if (user != null) {
                user.setEmail(newEmail);
                user.setPassword(newPassword);
                databaseHelper.getUserDAO().update(user);
                runOnUiThread(() -> Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void removeUser() {
        int userId = Integer.parseInt(etUserId.getText().toString());
        new Thread(() -> {
            User user = databaseHelper.getUserDAO().getUserById(userId);
            if (user != null) {
                databaseHelper.getUserDAO().delete(user);
                runOnUiThread(() -> Toast.makeText(this, "User removed successfully", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
