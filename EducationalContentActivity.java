package com.example.dogfoodapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EducationalContentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button backButton;
    private EducationalContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_content);

        recyclerView = findViewById(R.id.recyclerView);
        backButton = findViewById(R.id.backButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EducationalContentAdapter(getEducationalContent());
        recyclerView.setAdapter(adapter);


        backButton.setOnClickListener(v -> finish());
    }

    private List<EducationalContent> getEducationalContent() {
        List<EducationalContent> contentList = new ArrayList<>();
        contentList.add(new EducationalContent("Articles on Dog Nutrition", "“The Basics of Dog Nutrition”\n" +
                "\n" +
                "Overview of essential nutrients for dogs.\n" +
                "Explanation of macronutrients (proteins, fats, carbohydrates) and micronutrients (vitamins and minerals).\n" +
                "Importance of a balanced diet.\n" +
                "\n" +
                "“Choosing the Right Dog Food: What to Look For”\n" +
                "\n" +
                "Key factors to consider when selecting dog food.\n" +
                "Understanding ingredient labels and nutritional information.\n" +
                "Comparing different types of dog food (dry, wet, raw, and homemade).\n" +
                "\n" +
                "“Common Nutritional Deficiencies in Dogs”\n" +
                "\n" +
                "Signs and symptoms of common deficiencies.\n" +
                "How to address and prevent nutritional imbalances.\n" +
                "\n" +
                "“The Role of Supplements in Your Dog’s Diet”\n" +
                "\n" +
                "Types of supplements (e.g., omega fatty acids, probiotics).\n" +
                "Benefits and potential risks of supplementing your dog’s diet.\n" +
                "\n" +
                "“Understanding Dog Food Labels: A Guide for Pet Owners”\n" +
                "\n" +
                "How to interpret ingredient lists and nutritional panels.\n" +
                "What to look for and what to avoid.", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video1)));
        contentList.add(new EducationalContent("Guides on Dog Nutrition", "“Complete Guide to Dog Diets”\n" +
                "\n" +
                "Detailed breakdown of different types of dog diets (commercial, raw, home-cooked).\n" +
                "Pros and cons of each diet type.\n" +
                "Recommendations based on different life stages and health conditions.\n" +
                "\n" +
                "“How to Transition Your Dog to a New Diet”\n" +
                "\n" +
                "Step-by-step guide to safely changing your dog’s food.\n" +
                "Tips to minimize digestive upset and ensure a smooth transition.\n" +
                "\n" +
                "“Homemade Dog Food Recipes”\n" +
                "\n" +
                "Nutritious recipes for homemade dog food.\n" +
                "Guidelines for balancing nutrients in homemade meals.\n" +
                "\n" +
                "“Feeding Guidelines for Dogs: How Much and How Often”\n" +
                "\n" +
                "Recommendations for portion sizes based on dog size, age, and activity level.\n" +
                "Guidelines for feeding frequency and managing weight.\n" +
                "\n" +
                "“Nutrition for Puppies, Adult Dogs, and Seniors”\n" +
                "\n" +
                "Special dietary needs at different life stages.\n" +
                "Tailoring your dog’s diet to their age and activity level.", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video2)));
        contentList.add(new EducationalContent("Dog Breeds", "Labrador Retriever\n" +
                "\n" +
                "Size: Medium to large\n" +
                "Coat: Short and dense\n" +
                "Temperament: Friendly, outgoing, and high energy\n" +
                "Common Health Issues: Hip dysplasia, elbow dysplasia, obesity\n" +
                "\n" +
                "German Shepherd\n" +
                "\n" +
                "Size: Large\n" +
                "Coat: Medium length, double coat\n" +
                "Temperament: Intelligent, courageous, and loyal\n" +
                "Common Health Issues: Hip dysplasia, elbow dysplasia, digestive problems\n" +
                "\n" +
                "Beagle\n" +
                "\n" +
                "Size: Small to medium\n" +
                "Coat: Short and smooth\n" +
                "Temperament: Curious, friendly, and merry\n" +
                "Common Health Issues: Obesity, hip dysplasia, ear infections\n" +
                "\n" +
                "Bulldog\n" +
                "\n" +
                "Size: Medium\n" +
                "Coat: Short and smooth\n" +
                "Temperament: Calm, courageous, and friendly\n" +
                "Common Health Issues: Breathing problems, hip dysplasia, skin conditions\n" +
                "\n" +
                "Poodle\n" +
                "\n" +
                "Size: Miniature, Standard, and Toy\n" +
                "Coat: Curly and hypoallergenic\n" +
                "Temperament: Intelligent, active, and alert\n" +
                "Common Health Issues: Hip dysplasia, epilepsy, thyroid problems", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video3)));


        contentList.add(new EducationalContent("Life Stages", "Puppy (0-1 year)\n" +
                "\n" +
                "Diet: High in protein and calories to support growth and development\n" +
                "Exercise: Short, frequent play sessions and socialization\n" +
                "Health Care: Vaccinations, deworming, and regular vet check-ups\n" +
                "\n" +
                "Adult (1-7 years)\n" +
                "\n" +
                "Diet: Balanced diet appropriate for the breed and size; maintain ideal weight\n" +
                "Exercise: Regular exercise based on breed needs\n" +
                "Health Care: Annual vet visits, dental care, and preventative medications\n" +
                "\n" +
                "Senior (7+ years)\n" +
                "\n" +
                "Diet: Senior formula dog food with lower calories and joint support\n" +
                "Exercise: Gentle, low-impact activities; monitor for joint issues\n" +
                "Health Care: More frequent vet visits, monitor for age-related issues (e.g., arthritis, dental disease)", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video4)));


        contentList.add(new EducationalContent("Dietary Requirements", "Protein: Essential for muscle development and repair. Look for high-quality animal proteins.\n" +
                "Fat: Provides energy and supports skin and coat health. Ensure a balance of Omega-3 and Omega-6 fatty acids.\n" +
                "Carbohydrates: Source of energy and fiber. Whole grains or vegetables are good sources.\n" +
                "Vitamins and Minerals: Essential for overall health. Ensure a balanced intake through commercial dog food or supplements as needed.\n" +
                "Water: Always provide fresh, clean water.", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video5)));
        contentList.add(new EducationalContent("Health Tips", "Regular Exercise: Tailor exercise to your dog’s age, breed, and health status.\n" +
                "Dental Care: Brush teeth regularly and provide dental chews or toys.\n" +
                "Grooming: Regular brushing, bathing, and nail trimming as needed based on the breed.\n" +
                "Preventative Care: Regular vaccinations, flea/tick control, and heartworm prevention.\n" +
                "Monitoring Health: Watch for changes in behavior, appetite, or bathroom habits and consult a vet if concerns arise.", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video6)));


        return contentList;
    }


}
