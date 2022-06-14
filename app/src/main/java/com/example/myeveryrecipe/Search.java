package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    ImageView arrow;
    SearchView searchView;

    ArrayList<Drawable> imageList = new ArrayList<Drawable>();
    ImageView imageView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(Search.this, "검색결과 : "+query, Toast.LENGTH_SHORT).show();
                //searchWeb(query);
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q="+query));
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://m.blog.naver.com/SectionPostSearch.naver?orderType=sim&searchValue="+query+" 요리"));
                startActivity(browserIntent);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(Search.this, "검색중 : "+newText , Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Resources res = getResources();
        imageList.add(res.getDrawable(R.drawable.recommend_menu));
        imageList.add(res.getDrawable(R.drawable.tip));
        imageList.add(res.getDrawable(R.drawable.ppp));
        imageList.add(res.getDrawable(R.drawable.sugarrr));

        imageView = (ImageView) findViewById(R.id.imageView3);

        AnimThread thread = new AnimThread();
        thread.start();



    }
    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    class AnimThread extends Thread {
        @Override
        public void run() {
            while(true){
                for(int i=0; i<4; i++) {
                    int curIdx = i%5;
                    final Drawable drawable = imageList.get(curIdx);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageDrawable(drawable);
                        }
                    });

                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}