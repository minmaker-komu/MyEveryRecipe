package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPage extends AppCompatActivity {

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;


    private Context mContext;

    final String TAG = this.getClass().getSimpleName();
    private String name;

    TextView nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        mContext = this;

        // 인텐트 받아서 네임 값 저장
//        Intent intent = getIntent();
//        name = intent.getStringExtra("name");
//        Log.v(TAG,"name" + name);
//        System.out.println(name);

        //String checkName = PreferenceManager.getString(mContext, "name");
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        nickname = findViewById(R.id.nickname);
        nickname.setText(name);

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

    }
}