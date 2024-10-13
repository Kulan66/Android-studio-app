package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private RadioGroup roleGroup;
    private RadioButton userRadioButton, adminRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        roleGroup = findViewById(R.id.roleGroup);
        userRadioButton = findViewById(R.id.userRadioButton);
        adminRadioButton = findViewById(R.id.adminRadioButton);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            int selectedRoleId = roleGroup.getCheckedRadioButtonId();

            if (selectedRoleId == -1) {
                Toast.makeText(LoginActivity.this, "Please select a role", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email.isEmpty() && !password.isEmpty()) {
                if (selectedRoleId == R.id.userRadioButton) {
                    handleUserLogin(email, password);
                } else if (selectedRoleId == R.id.adminRadioButton) {
                    handleAdminLogin(email, password);
                }
            } else {
                Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void handleUserLogin(String email, String password) {
        UserDAO userDAO = DatabaseHelper.getInstance(LoginActivity.this).getUserDAO();

        new Thread(() -> {
            User user = userDAO.getUserByEmailAndPassword(email, password);
            runOnUiThread(() -> {
                if (user != null) {
                    Toast.makeText(LoginActivity.this, "User login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, UserPageActivity.class);
                    intent.putExtra("USER_ID", user.getId());
                    startActivity(intent);
                    finish(); // Close the login activity
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
    private void handleAdminLogin(String email, String password){
        AdminDAO adminDAO = DatabaseHelper.getInstance(LoginActivity.this).getAdminDAO();

        new Thread(() -> {
            Admin admin = adminDAO.getAdminByEmailAndPassword(email, password);
            runOnUiThread(() -> {
                if (admin != null) {
                    Toast.makeText(LoginActivity.this, "Admin login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    intent.putExtra("ADMIN_ID", admin.getId());
                    startActivity(intent);
                    finish(); // Close the login activity
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}