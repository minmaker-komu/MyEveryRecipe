package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadMaterial extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ImageView arrow;
    EditText material_title;
    TextView material_date;
    TextView material_date2;
    ImageView material_img;
    TextView edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_material);

        // 뒤로가기
        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                startActivity(intent);
            }
        });

        /*// 저장해둔 값 불러오기

        String mName = preferences.getString("material_name","");
        String mDate = preferences.getString("material_date","");
        String mDate2 = preferences.getString("material_date2","");

        // 레이아웃 변수 초기화

        material_title = findViewById(R.id.material_show);
        material_date = findViewById(R.id.dateInsert);
        material_date2 = findViewById(R.id.dateInsert2);

        // 앱을 새로 켜면 이전에 저장해둔 값이 표시됨됨
        material_title.setText(String.valueOf(mName));
        material_date.setText(String.valueOf(mDate));
        material_date2.setText(String.valueOf(mDate2));*/



        // 재료 수정
        edit_text = findViewById(R.id.edit_text);
        edit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이름 업데이트
                String name_update = material_title.getText().toString();
                String date_update = material_date.getText().toString();
                String date2_update = material_date2.getText().toString();

                editor.putString("material_name",name_update);
                editor.putString("material_date",date_update);
                editor.putString("material_date2",date2_update);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                //intent.putExtra("material_name_update",name_update);
                //intent.putExtra("position", getIntent().getIntExtra("position", -1));

                startActivity(intent);
                finish();


            }
        });
    }
}