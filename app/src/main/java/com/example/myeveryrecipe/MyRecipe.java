package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyRecipe extends AppCompatActivity {

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;

    FloatingActionButton btn;

    MyRecipeAdapter adapter;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe);

        // 정의
        cook = findViewById(R.id.menu_cook);
        refri = findViewById(R.id.menu_refri);
        recipe = findViewById(R.id.menu_writing);
        mypage = findViewById(R.id.menu_profile);

        // 홈
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        // 냉장고
        refri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                startActivity(intent);
            }
        });
        // 내 레시피
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                startActivity(intent);
            }
        });
        // 마이 페이지
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyPage.class);
                //intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.recipe_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Writing.class);
                startActivity(intent);
            }
        });

        // 리사이클러뷰
        RecyclerView recyclerView = findViewById(R.id.myRecipe_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MyRecipeAdapter(mList);
        recyclerView.setAdapter(adapter);
        System.out.println("$$$$");

        // 아이템 추가.
        addItem(R.drawable.gamberoni,"감베로니","양식");
        System.out.println("&&");
        // 두 번째 아이템 추가.
        addItem(R.drawable.susi, "연어초밥","기타");
        // 세 번째 아이템 추가.
        addItem(R.drawable.oilpasta, "삼겹살 파스타","양식");
        adapter.notifyDataSetChanged();
    }

    public void addItem(int recipe_image, String recipe_title, String recipe_food) {
        MyRecipeData item = new MyRecipeData(recipe_image,recipe_title,recipe_food);
        mList.add(item);
        System.out.println("%%%%");
    }
}