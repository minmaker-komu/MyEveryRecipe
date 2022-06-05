package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Salad extends AppCompatActivity {
    ImageView arrow;
    RecyclerView mRecyclerView = null ;
    RecyclerRecipeAdapter mAdapter = null ;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad);

        mRecyclerView = findViewById(R.id.recycler4);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerRecipeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 아이템 추가
        addItem(R.drawable.susi, "연어초밥","기타","","");

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
    public void addItem(int recipe_image, String recipe_title, String recipe_food, String recipe_need, String recipe_context) {
        MyRecipeData item = new MyRecipeData(recipe_image,recipe_title,recipe_food,recipe_need, recipe_context);
        mList.add(item);
        System.out.println("%%%%");
    }
}