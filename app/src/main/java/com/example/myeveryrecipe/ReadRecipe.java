package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadRecipe extends AppCompatActivity {

    ImageView arrow;

    TextView recipe_title, recipe_need, recipe_context;
    ImageView recipe_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_recipe);

        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        // 앍어오기
        recipe_title = findViewById(R.id.recipe_show);
        recipe_img = findViewById(R.id.recipe_img_show);
        recipe_need = findViewById(R.id.editFOOD);
        recipe_context = findViewById(R.id.editRecipe);
        String title = getIntent().getStringExtra("title");
        //String food = getIntent().getStringExtra("title");
        String need = getIntent().getStringExtra("need");
        String recipe = getIntent().getStringExtra("recipe");
        int img = getIntent().getIntExtra("img2",0);
        recipe_title.setText(title);
        recipe_img.setImageResource(img);
        recipe_need.setText(need);
        recipe_context.setText(recipe);
    }
}