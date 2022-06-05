package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyPage extends AppCompatActivity {

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;

    // 스크랩한 레시피
    RecyclerView mRecyclerView = null ;
    RecyclerRecipeAdapter mAdapter = null ;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();
    // 스크랩 레시피 전체 보기
    TextView scrap;


    private Context mContext;

    final String TAG = this.getClass().getSimpleName();
    private String name;

    TextView nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        mContext = this;

        mRecyclerView = findViewById(R.id.recycler_scrap);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerRecipeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        addItem(R.drawable.susi, "연어초밥","기타","","");
        addItem(R.drawable.oilpasta, "파스타","양식","","");
        addItem(R.drawable.pasta2, "새우 베이컨 파스타","양식","","");
        addItem(R.drawable.pasta3, "토마토 파스타","양식","","");

        mAdapter.notifyDataSetChanged() ;

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        nickname = findViewById(R.id.nickname);
        nickname.setText(name);

        // 정의
        cook = findViewById(R.id.menu_cook);
        refri = findViewById(R.id.menu_refri);
        recipe = findViewById(R.id.menu_writing);
        mypage = findViewById(R.id.menu_profile);
        scrap = findViewById(R.id.show_all_recipe);

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
        scrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Scrap.class);
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