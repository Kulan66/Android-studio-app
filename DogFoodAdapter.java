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

public class DogFoodAdapter extends RecyclerView.Adapter<DogFoodAdapter.ViewHolder> {

    private final List<DogFood> dogFoodList;
    private final Context context;
    private Button  userCartButton;

    public DogFoodAdapter(List<DogFood> dogFoodList, Context context) {
        this.dogFoodList = dogFoodList;
        this.context = context;
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dog_food, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DogFood dogFood = dogFoodList.get(position);
        holder.nameTextView.setText(dogFood.getName());
        holder.priceTextView.setText(String.valueOf(dogFood.getPrice()));
        holder.descriptionTextView.setText(dogFood.getDescription());

        holder.addToCartButton.setOnClickListener(v ->{
            CartDAO cartDAO = DatabaseHelper.getInstance(context).getCartDAO();
            new Thread(() -> {
                CartItem cartItem = new CartItem();
                cartItem.setDogFoodId(dogFood.getId());
                cartItem.setQuantity(1);  // Default quantity is 1
                cartDAO.insert(cartItem);
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() ->
                            Toast.makeText(context, "Added done", Toast.LENGTH_SHORT).show());
                }
            }).start();
        });

        }

    @Override
    public int getItemCount() {
        return dogFoodList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, descriptionTextView;
        Button addToCartButton;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        addToCartButton = itemView.findViewById(R.id.addToCartButton);
    }

    }
}