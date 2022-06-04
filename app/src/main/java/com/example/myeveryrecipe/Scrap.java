package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Scrap extends AppCompatActivity {

    ImageView arrow;
    RecyclerView mRecyclerView = null ;
    RecyclerRecipeAdapter mAdapter = null ;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap);

        mRecyclerView = findViewById(R.id.recycler_all_scrap);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerRecipeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addItem(R.drawable.susi, "연어초밥","기타");
        addItem(R.drawable.oilpasta, "파스타","양식");
        addItem(R.drawable.pasta2, "새우 베이컨 파스타","양식");
        addItem(R.drawable.pasta3, "토마토 파스타","양식");
        mAdapter.notifyDataSetChanged();


        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MyPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
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