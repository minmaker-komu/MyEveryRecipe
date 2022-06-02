package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Writing extends AppCompatActivity {

    Spinner spinner;

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
    }
}