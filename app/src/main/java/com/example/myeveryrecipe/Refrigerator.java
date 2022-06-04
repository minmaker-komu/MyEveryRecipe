package com.example.myeveryrecipe;




import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Refrigerator extends AppCompatActivity {
    TextView date;

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;

    FloatingActionButton material_add;

    ArrayList<MaterialData> materialData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

        // 정의
        cook = findViewById(R.id.menu_cook);
        refri = findViewById(R.id.menu_refri);
        recipe = findViewById(R.id.menu_writing);
        mypage = findViewById(R.id.menu_profile);

        material_add = findViewById(R.id.refrigerator);
        material_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Material.class);
                startActivity(intent);
            }
        });

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
                startActivity(intent);
            }
        });

        // 현재 날짜
        date = findViewById(R.id.textDate);
        date.setText(getTime());

        // 초기에 보여질 재료들
        this.InitializeMaterialData();

        // 리스트뷰 보이게
        ListView listView = (ListView)findViewById(R.id.listview);
        final MaterialAdapter materialAdapter = new MaterialAdapter(this,materialData);
        listView.setAdapter(materialAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //materialAdapter.getItem(position).getMaterial_name();
                Intent intent = new Intent(getApplicationContext(), ReadMaterial.class);
                startActivity(intent);
            }
        });

        // 길게 클릭했을 때 삭제하기
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Refrigerator.this);
                builder.setTitle("재료 삭제하기")
                        .setMessage(materialData.get(position).getMaterial_name() + "을(를) 삭제하시겠습니까?")
                        .setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                materialData.remove(position);
                                materialAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNeutralButton("취소", null).show();
                return false;
            }
        });
    }

    public void InitializeMaterialData()
    {
        materialData = new ArrayList<MaterialData>();

        materialData.add(new MaterialData(R.drawable.bread, "빵","22.05.06","23.09.11","2"));
    }

    private String getTime() { long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date); return getTime;
    }
}