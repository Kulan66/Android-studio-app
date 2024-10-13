package com.example.dogfoodapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<CartItem> cartItemList;
    private final Context context;

    public CartAdapter(List<CartItem> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context = context;
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        DogFoodDAO dogFoodDAO = DatabaseHelper.getInstance(context).getDogFoodDAO();
        new Thread(() -> {
            DogFood dogFood = dogFoodDAO.getDogFoodById(cartItem.getDogFoodId());
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(() -> {
                    if (dogFood != null) {
                        holder.nameTextView.setText(dogFood.getName());
                        holder.priceTextView.setText(String.valueOf(dogFood.getPrice()));
                    }
                });
            }
        }).start();
        holder.increaseButton.setOnClickListener(v -> {
            int newQuantity = cartItem.getQuantity() + 1;
            cartItem.setQuantity(newQuantity);
            new Thread(() -> {
                CartDAO cartDAO = DatabaseHelper.getInstance(context).getCartDAO();
                cartDAO.update(cartItem);
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        notifyDataSetChanged();
                        Toast.makeText(context, "Quantity increased", Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();
        });

        holder.decreaseButton.setOnClickListener(v -> {
            int newQuantity = cartItem.getQuantity() - 1;
            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                new Thread(() -> {
                    CartDAO cartDAO = DatabaseHelper.getInstance(context).getCartDAO();
                    cartDAO.update(cartItem);
                    if (context instanceof Activity) {
                        ((Activity) context).runOnUiThread(() -> {
                            notifyDataSetChanged();
                            Toast.makeText(context, "Quantity decreased", Toast.LENGTH_SHORT).show();
                        });
                    }
                }).start();
            } else {
                new Thread(() -> {
                    CartDAO cartDAO = DatabaseHelper.getInstance(context).getCartDAO();
                    cartDAO.delete(cartItem);
                    if (context instanceof Activity) {
                        ((Activity) context).runOnUiThread(() -> {
                            notifyDataSetChanged();
                            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
                        });
                    }
                }).start();
            }
        });
    }
    @Override
    public int getItemCount() {
        return cartItemList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        Button increaseButton, decreaseButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            increaseButton = itemView.findViewById(R.id.increaseButton);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
        }
    }
}



