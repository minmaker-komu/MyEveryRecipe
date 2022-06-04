package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadMyRecipe extends AppCompatActivity {

    SharedPreferences pref; //프리퍼런스
    SharedPreferences.Editor editor; //에디터

    ImageView arrow;
    EditText recipe_title;
    ImageView recipe_img;
    TextView edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_my_recipe);

        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                startActivity(intent);
            }
        });

        //pref = getSharedPreferences("")




        // 레시피 제목 수정
        edit_text = findViewById(R.id.edit_text);
        edit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_update = recipe_title.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                intent.putExtra("edit", true);
                intent.putExtra("name_update",name_update);
                intent.putExtra("position", getIntent().getIntExtra("position", -1));
                System.out.println("*****");
                startActivity(intent);
                finish();


            }
        });

    }
}