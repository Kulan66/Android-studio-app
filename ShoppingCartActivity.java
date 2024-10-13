package com.example.dogfoodapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button backButton;
    private CartAdapter adapter;
    private List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.recyclerView);
        backButton = findViewById(R.id.backButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CartDAO cartDAO = DatabaseHelper.getInstance(this).getCartDAO();
        new Thread(() -> {
            cartItemList = cartDAO.getAllCartItems();
            runOnUiThread(() -> {
                adapter = new CartAdapter(cartItemList, this);
                recyclerView.setAdapter(adapter);
            });
        }).start();

        backButton.setOnClickListener(v -> finish());
    }
}
