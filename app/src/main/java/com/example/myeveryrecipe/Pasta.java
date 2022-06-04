package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Pasta extends AppCompatActivity {

    ImageView arrow;
    RecyclerView mRecyclerView = null ;
    RecyclerRecipeAdapter mAdapter = null ;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta);

        mRecyclerView = findViewById(R.id.recycler2);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerRecipeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 아이템 추가
        addItem(R.drawable.gamberoni, "감베로니","양식");
        // 두 번째 아이템 추가.
        addItem(R.drawable.pasta2, "새우 베이컨 파스타","양식");
        // 세 번째 아이템 추가.
        addItem(R.drawable.pasta3, "토마토 파스타","양식");

        mAdapter.notifyDataSetChanged() ;

        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addItem(int recipe_image, String recipe_title, String recipe_food) {
        MyRecipeData item = new MyRecipeData(recipe_image,recipe_title,recipe_food);

        item.setRecipe_image(recipe_image);
        item.setRecipe_name(recipe_title);
        item.setRecipe_food(recipe_food);

        mList.add(item);
    }
}