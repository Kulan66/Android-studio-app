package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button registerButton, goToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        goToLoginButton = findViewById(R.id.goToLoginButton);

        new Thread(() -> {
            AdminDAO adminDAO = DatabaseHelper.getInstance(MainActivity.this).getAdminDAO();
            Admin admin = adminDAO.getAdminByEmailAndPassword("admin", "admin123");

            if (admin == null) {
                // Admin doesn't exist, so create it
                Admin newAdmin = new Admin();
                newAdmin.setEmail("admin");
                newAdmin.setPassword("admin123");
                adminDAO.insert(newAdmin);
            }
        }).start();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    UserDAO userDAO = DatabaseHelper.getInstance(MainActivity.this).getUserDAO();

                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);

                    new Thread(() -> {
                        userDAO.insert(user);
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        });
                    }).start();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goToLoginButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }
}
