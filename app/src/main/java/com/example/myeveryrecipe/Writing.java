package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Writing extends AppCompatActivity {

    Spinner spinner;

    TextView textCancel_btn;
    TextView textRegister_btn;

    EditText editTitle, editFood, editRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        // 스피너 선언
        spinner = findViewById(R.id.spinner);
        // 배열 선언
        String[] food = getResources().getStringArray(R.array.food_array);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(), R.layout.spinner_item,food);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String whatFood = food[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textCancel_btn = findViewById(R.id.textCancel);
        textCancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Writing.this, "레시피 쓰기를 종료합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                startActivity(intent);
                finish();
            }
        });

        // 레시피 등록 버튼
        textRegister_btn = findViewById(R.id.textRegister);
        textRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editTitle.getText().toString();
                Intent intent = new Intent(Writing.this, MyRecipe.class);
                intent.putExtra("name", title);
                System.out.println(title);
                //intent.putExtra("image", image );
                //startActivityForResult(intent,100);
                setResult(RESULT_OK,intent);
                System.out.println("222222");
                //startActivity(intent);
                Toast.makeText(Writing.this, "레시피 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}