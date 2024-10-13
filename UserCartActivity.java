package com.example.dogfoodapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button checkoutButton;
    private CartAdapter adapter;
    private List<CartItem> cartItemList;
    private TextView totalPriceTextView;
    private double totalPrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cart);

        recyclerView = findViewById(R.id.cartRecyclerView);
        checkoutButton = findViewById(R.id.checkoutButton);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CartDAO cartDAO = DatabaseHelper.getInstance(this).getCartDAO();
        new Thread(() -> {
            cartItemList = cartDAO.getAllCartItems();
            DogFoodDAO dogFoodDAO = DatabaseHelper.getInstance(this).getDogFoodDAO();
            for (CartItem cartItem : cartItemList) {
                DogFood dogFood = dogFoodDAO.getDogFoodById(cartItem.getDogFoodId());
                if (dogFood != null) {
                    totalPrice += dogFood.getPrice() * cartItem.getQuantity();
                }
            }
            runOnUiThread(() -> {
                adapter = new CartAdapter(cartItemList, this);
                recyclerView.setAdapter(adapter);
                totalPriceTextView.setText("Total Price: RS." + String.format("%.2f", totalPrice));
            });
        }).start();
        checkoutButton.setOnClickListener(v -> {
            Toast.makeText(UserCartActivity.this, "Order successful", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

}
