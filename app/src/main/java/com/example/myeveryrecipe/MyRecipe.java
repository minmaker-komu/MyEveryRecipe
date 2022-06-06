package com.example.myeveryrecipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyRecipe extends AppCompatActivity {

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;
    public static Context context;

    FloatingActionButton btn;
    Gson gson;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    MyRecipeAdapter adapter;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe);

        sharedPreferences = getSharedPreferences("myrecipe", MODE_PRIVATE);

        //context = getApplicationContext();
        System.out.println("****create 되었습니다****");

        // 정의
        cook = findViewById(R.id.menu_cook);
        refri = findViewById(R.id.menu_refri);
        recipe = findViewById(R.id.menu_writing);
        mypage = findViewById(R.id.menu_profile);

        // 홈
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        // 냉장고
        refri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Refrigerator.class);
                startActivity(intent);
            }
        });
        // 내 레시피
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipe.class);
                startActivity(intent);
            }
        });
        // 마이 페이지
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                //intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.recipe_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyRecipe.this, Writing.class);
                startActivityForResult(intent, 7);
            }
        });

        // 리사이클러뷰
        RecyclerView recyclerView = findViewById(R.id.myRecipe_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) linearLayoutManager).setReverseLayout(true);
        ((LinearLayoutManager) linearLayoutManager).setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);


        // 아이템 추가.
        addItem(R.drawable.gamberoni, "감베로니", "양식", "", "");
        System.out.println("&&");
        // 두 번째 아이템 추가.
        addItem(R.drawable.susi, "연어초밥", "기타", "", "");
        // 세 번째 아이템 추가.
        addItem(R.drawable.oilpasta, "삼겹살 파스타", "양식", "", "");

        readData();
        adapter = new MyRecipeAdapter(mList);
        recyclerView.setAdapter(adapter);
        System.out.println("$$$$");
        adapter.notifyDataSetChanged();
    }

    public void addItem(int recipe_image, String recipe_title, String recipe_food, String recipe_need, String recipe_context) {
        MyRecipeData item = new MyRecipeData(recipe_image, recipe_title, recipe_food, recipe_need, recipe_context);
        mList.add(item);
        System.out.println("%%%%");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);
        if (resultCode == RESULT_OK) {
            System.out.println("@@@@@");

            String title = data.getStringExtra("name");
            String food = data.getStringExtra("food");
            String recipe = data.getStringExtra("recipe");
            String need = data.getStringExtra("need");
            mList.add(new MyRecipeData(R.drawable.susi, title, food, recipe, need));
            System.out.println("%%%%" + title);
            adapter.notifyDataSetChanged();
            System.out.println("추가완료******ㅇㅁㄴ");
            //mAdapter.notifyItemChanged();
            //image = getIntent().getIntExtra("image",0);
            /*switch (food) {
                case "한식":
                    // 한식이면 한식 액티비티에도 리사이클러뷰 추가하기
                    System.out.println("한식입니다ㅇㄴ");
                    Intent intent = new Intent(this, Korean.class);
                    intent.putExtra("name", title);
                    intent.putExtra("need", need);
                    intent.putExtra("recipe", recipe);
                    setResult(427, intent);
                    //finish();
                    break;
            }*/

        } else if (resultCode == 609) {
            System.out.println(resultCode);
            String title = data.getStringExtra("name_update");
            System.out.println(title);
            mList.get(adapter.getItemCount() - 1).setRecipe_name(title);
            //mAdapter.notifyItemChanged(mAdapter.getItemCount()-1,title);
            adapter.notifyDataSetChanged();


        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        int action = 0;
        //if (getIntent().getBooleanExtra("new", false)) action = 1;
        if (getIntent().getBooleanExtra("edit", false)) action = 2;
        // 수정
        if (action > 0) {
            System.out.println("^^^^^");

            String name_update = getIntent().getStringExtra("name_update");
            String need_update = getIntent().getStringExtra("need_update");
            String context_update = getIntent().getStringExtra("context_update");
            System.out.println(name_update);
            int position = getIntent().getIntExtra("position", -1);
            System.out.println(position);
            System.out.println("%%%");

            if (position != -1) {
                System.out.println("####");
                mList.get(position).setRecipe_name(name_update);
                mList.get(position).setRecipe_need(need_update);
                mList.get(position).setRecipe_context(context_update);
            }
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("****stop 되었습니다****");
        saveData(mList);
    }

    public void saveData(ArrayList<MyRecipeData> mList) {
        sharedPreferences = getSharedPreferences("myrecipe", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String json = gson.toJson(mList);

        editor.putString("recipe_list", json);
        editor.apply();

    }

    public ArrayList<MyRecipeData> readData() {

        SharedPreferences sharedPreferences = getSharedPreferences("myrecipe", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("recipe_list", "");
        Type type = new TypeToken<ArrayList<MyRecipeData>>() {
        }.getType();
        mList = gson.fromJson(json, type);
        //adapter = new MyRecipeAdapter(mList);

        //return mData;
        if (mList == null) {
            mList = new ArrayList<>();
        }
        return mList;
    }
}