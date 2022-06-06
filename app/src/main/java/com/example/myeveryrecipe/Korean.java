package com.example.myeveryrecipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Korean extends AppCompatActivity {

    ImageView arrow;
    RecyclerView mRecyclerView = null ;
    RecyclerRecipeAdapter mAdapter = null ;
    ArrayList<MyRecipeData> mList = new ArrayList<MyRecipeData>();
    //ArrayList<RecipeData> mmlist = new ArrayList<RecipeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean);

        mRecyclerView = findViewById(R.id.recycler1);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerRecipeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 아이템 추가
        addItem(R.drawable.korean1, "낙지볶음","한식","낙지, 양파, 파","");


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

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);
        if(requestCode == 428){
            if (resultCode == 427) {
                System.out.println("@@@@@");

                String title = data.getStringExtra("name");
                String food = data.getStringExtra("food");
                String recipe = data.getStringExtra("recipe");
                String need = data.getStringExtra("need");
                mList.add(new MyRecipeData(R.drawable.susi, title, food, recipe, need));
                System.out.println("%%%%" + title);
                mAdapter.notifyDataSetChanged();
                System.out.println("추가완료WlsWls");
                //mAdapter.notifyItemChanged();
                //image = getIntent().getIntExtra("image",0);
            }
        }


    }*/
}