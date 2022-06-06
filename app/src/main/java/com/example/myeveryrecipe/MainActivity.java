package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();
    private String name;

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;

    // 홈화면 구성 이미지
    ImageView search;
    ImageView korean;
    ImageView pasta;
    ImageView sandwich;
    ImageView salad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 인텐트 받아서 네임 값 저장
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.v(TAG,"name" + name);
        System.out.println(name);

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
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        // 홈화면 구성 버튼
        search = findViewById(R.id.search_btn);
        korean = findViewById(R.id.image_korean);
        pasta = findViewById(R.id.image_pasta);
        sandwich = findViewById(R.id.image_sandwich);
        salad = findViewById(R.id.image_salad);

        // 검색창 버튼 눌렀을때
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
            }
        });
        // 한식 버튼 눌렀을 때
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Korean.class);
                startActivityForResult(intent, 428);
                //startActivity(intent);
            }
        });
        // 양식 버튼 눌렀을 때
        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Pasta.class);
                startActivity(intent);
            }
        });
        // 간식 버튼 눌렀을 때
        sandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Sandwich.class);
                startActivity(intent);
            }
        });
        // 기타 버튼 눌렀을 때
        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Salad.class);
                startActivity(intent);
            }
        });
    }
}