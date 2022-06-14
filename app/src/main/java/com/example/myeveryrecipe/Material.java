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
import android.widget.Toast;

import java.util.Calendar;

public class Material extends AppCompatActivity {

    TextView materialCancel;
    TextView materialRegister;

    ImageView imageView;
    private final int GET_GALLERY_IMAGE = 1;

    EditText name;
    Button date_btn;
    TextView date;
    String material_name, material_date, material_date2;
    Button date_btn2;
    TextView date2;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        name = findViewById(R.id.edit_materialName);

        materialCancel = findViewById(R.id.textCancel);
        materialCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Material.this, "재료 등록을 취소했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });

        // 재료 등록 버튼
        materialRegister = findViewById(R.id.textRegister);
        materialRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 객체 전달
                material_name = name.getText().toString();
                material_date2 = date2.getText().toString();
                material_date = date.getText().toString();
                if (name.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                    intent.putExtra("mname", material_name);
                    intent.putExtra("date",material_date);
                    intent.putExtra("date2",material_date2);
                    setResult(800,intent);
                    System.out.println("2222222");
                    Toast.makeText(Material.this, "재료 등록을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                Toast.makeText(Material.this, "재료 등록을 완료했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        date = findViewById(R.id.dateInsert);
        date_btn = findViewById(R.id.button_date);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(view == date_btn){
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Material.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                    date.setText(year + "." + (month+1) + "." + dayOfMonth);
                                }

                            }, mYear,mMonth,mDay);
                    datePickerDialog.show();
                }

            }
        });

        date2 = findViewById(R.id.dateInsert2);
        date_btn2 = findViewById(R.id.button_date2);
        date_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(view == date_btn2){
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Material.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                    int year1 = year;
                                    int month1 = month;
                                    int dayOfMonth1 = dayOfMonth;
                                    date2.setText(year + "." + (month+1) + "." + dayOfMonth);
                                }

                            }, mYear,mMonth,mDay);
                    datePickerDialog.show();
                }

            }
        });

        imageView = findViewById(R.id.imageMaterial);
        imageView.setOnClickListener(new View.OnClickListener() {
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
            imageView.setImageURI(selectedImageUri);
        }
    }
}