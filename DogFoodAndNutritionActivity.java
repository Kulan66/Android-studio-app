package com.example.dogfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogFoodAndNutritionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button shoppingCartButton;
    private DogFoodAdapter adapter;
    private List<DogFood> dogFoodList;
    private Button cartButton, userCartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_food_and_nutrition);

        userCartButton = findViewById(R.id.userCartButton);

        recyclerView = findViewById(R.id.recyclerView);
        shoppingCartButton = findViewById(R.id.shoppingCartButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Insert sample data if the database is empty
        new Thread(() -> {
            DogFoodDAO dogFoodDAO = DatabaseHelper.getInstance(this).getDogFoodDAO();
            List<DogFood> allDogFoods = dogFoodDAO.getAllDogFood();
            if (allDogFoods.isEmpty()) {
                insertSampleData();
            }
        }).start();

        DogFoodDAO dogFoodDAO = DatabaseHelper.getInstance(this).getDogFoodDAO();
        new Thread(() -> {
            dogFoodList = dogFoodDAO.getAllDogFood();
            runOnUiThread(() -> {
                adapter = new DogFoodAdapter(dogFoodList, this);
                recyclerView.setAdapter(adapter);
            });
        }).start();
        shoppingCartButton.setOnClickListener(v -> startActivity(new Intent(DogFoodAndNutritionActivity.this, ShoppingCartActivity.class)));
        userCartButton.setOnClickListener(v -> startActivity(new Intent(DogFoodAndNutritionActivity.this, UserCartActivity.class)));
    }

    private void insertSampleData() {
        DogFoodDAO dogFoodDAO = DatabaseHelper.getInstance(this).getDogFoodDAO();
        new Thread(() -> {
            List<DogFood> sampleDogFoods = List.of(
                    new DogFood("Royal Canin Size Health Nutrition Small Adult", "Brand: Royal Canin\n" +
                            "Type: Dry Dog Food\n" +
                            "Age: Adult\n" +
                            "Size: Small\n" +
                            "Specially formulated to meet the nutritional needs of small breed adult dogs. Includes high-quality protein to support muscle maintenance and easy-to-digest ingredients to ensure optimal digestion.\nCustomer Reviews:\n" +
                            "Rating: 4.7/5\n" +
                            "Review 1: \"My Chihuahua loves this food! It keeps her coat shiny and her energy levels high.\" – Jessica L.\n" +
                            "Review 2: \"Great for small breeds with sensitive stomachs. No more digestive issues!\" – Michael P.", 32433.00),

                    new DogFood("Hill’s Science Diet Adult Large Breed Chicken & Barley", "Brand: Hill’s Science Diet\n" +
                            "Type: Dry Dog Food\n" +
                            "Age: Adult\n" +
                            "Size: Large\n" +
                            "Provides essential nutrients and high-quality proteins to support joint health and muscle maintenance for large breed dogs. Contains chicken and barley for easy digestion and optimal energy levels.\nCustomer Reviews:\n" +
                            "Rating: 4.5/5\n" +
                            "Review 1: \"My Labrador’s coat is healthier than ever. Highly recommend for large breeds.\" – Sarah G.\n" +
                            "Review 2: \"Effective for managing weight and joint health. Great quality food!\" – Kevin R.", 33452.00),

                    new DogFood("Purina Pro Plan Savor Adult Shredded Blend Chicken & Rice", "Brand: Purina Pro Plan\n" +
                            "Type: Dry Dog Food\n" +
                            "Age: Adult\n" +
                            "Size: Medium\n" +
                            "A blend of crunchy kibble and tender, shredded pieces. Contains real chicken as the first ingredient and is fortified with live probiotics to support digestive health.\nCustomer Reviews:\n" +
                            "Rating: 4.6/5\n" +
                            "Review 1: \"My dog loves the mix of textures, and his digestion has improved significantly.\" – Emily K.\n" +
                            "Review 2: \"Excellent food with visible results in my dog’s overall health and coat condition.\" – Andrew T.", 49494.00),

                    new DogFood("Wellness CORE Grain-Free Beef & Pork", "Brand: Wellness\n" +
                            "Type: Dry Dog Food\n" +
                            "Age: Adult\n" +
                            "Size: All Sizes\n" +
                            "Grain-free formula packed with high-quality proteins and antioxidants to support lean body mass and muscle tone. Suitable for dogs with food sensitivities or allergies.\nCustomer Reviews:\n" +
                            "Rating: 4.8/5\n" +
                            "Review 1: \"Great for dogs with grain allergies. My dog has more energy and a shinier coat.\" – Laura M.\n" +
                            "Review 2: \"High-quality ingredients and no more itching or digestive issues.\" – David F.", 18113.00),

                    new DogFood("Blue Buffalo Wilderness High Protein Chicken", "Brand: Blue Buffalo\n" +
                            "Type: Dry Dog Food\n" +
                            "Age: Adult\n" +
                            "Size: All Sizes\n" +
                            "High-protein formula with real chicken as the first ingredient. Free from grains and artificial preservatives, designed to support lean muscle mass and energy levels.\nCustomer Reviews:\n" +
                            "Rating: 4.7/5\n" +
                            "Review 1: \"My dog loves this food, and his coat looks amazing. Great value for the price.\" – Natalie B.\n" +
                            "Review 2: \"Excellent protein content and natural ingredients. Highly recommend for active dogs.\" – James W.", 20049.00)
            );

            dogFoodDAO.insertAll(sampleDogFoods);
            runOnUiThread(() -> Toast.makeText(this, "Sample data inserted", Toast.LENGTH_SHORT).show());
        }).start();
    }

}
