package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class ReadMaterial extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ImageView arrow;
    EditText material_title;
    TextView material_date;
    TextView material_date2;
    ImageView material_img;
    private final int GET_GALLERY_IMAGE = 400;
    TextView edit_text;

    Button date_btn2, date_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_material);

        // 뒤로가기
        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                startActivity(intent);
            }
        });

        material_date = findViewById(R.id.dateInsert);
        date_btn = findViewById(R.id.button_date);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(view == date_btn){
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(ReadMaterial.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                    material_date.setText(year + "." + (month+1) + "." + dayOfMonth);
                                }

                            }, mYear,mMonth,mDay);
                    datePickerDialog.show();
                }

            }
        });

        material_date2 = findViewById(R.id.dateInsert2);
        date_btn2 = findViewById(R.id.button_date2);
        date_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(view == date_btn2){
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(ReadMaterial.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                    material_date2.setText(year + "." + (month+1) + "." + dayOfMonth);
                                }

                            }, mYear,mMonth,mDay);
                    datePickerDialog.show();
                }

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
                //int img_update = material_img.get

                /*editor.putString("material_name",name_update);
                editor.putString("material_date",date_update);
                editor.putString("material_date2",date2_update);
                editor.apply();*/


                Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                intent.putExtra("edit", true);
                intent.putExtra("name_update",name_update);
                intent.putExtra("date_update",date_update);
                intent.putExtra("date2_update",date2_update);
                intent.putExtra("position", getIntent().getIntExtra("position", -1));
                System.out.println("*****");

                startActivity(intent);
                finish();


            }
        });

        // 앍어오기
        material_title = findViewById(R.id.material_show);
        material_img = findViewById(R.id.imageMaterial);
        material_date = findViewById(R.id.dateInsert);
        material_date2 = findViewById(R.id.dateInsert2);
        String name = getIntent().getStringExtra("material_name");
        String date = getIntent().getStringExtra("material_date");
        String date2 = getIntent().getStringExtra("material_due");
        int num = getIntent().getIntExtra("material_num",1);
        int img = getIntent().getIntExtra("material_img",0);
        material_title.setText(name);
        material_img.setImageResource(img);
        material_date.setText(date);
        material_date2.setText(date2);

        material_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });
    }

    // 이미지 넣기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            material_img.setImageURI(selectedImageUri);
        }
    }
}