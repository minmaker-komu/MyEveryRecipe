package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Writing extends AppCompatActivity {

    Spinner spinner;

    TextView textCancel_btn;
    TextView textRegister_btn;
    private final int GET_GALLERY_IMAGE = 200;
    ImageView recipeImg;

    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    EditText editTitle, editFood, editRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        sharedPreferences = getSharedPreferences("Recipe", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // 스피너 선언
        spinner = findViewById(R.id.spinner);
        // 배열 선언
        //String[] food = getResources().getStringArray(R.array.food_array);

        //ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(), R.layout.spinner_item,food);
        //arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        //spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //String whatFood = food[position].toString();
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

        editTitle = findViewById(R.id.editTitle);
        editFood = findViewById(R.id.editFOOD);
        editRecipe = findViewById(R.id.editRecipe);

        // 레시피 등록 버튼
        textRegister_btn = findViewById(R.id.textRegister);
        textRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 레시피 이름
                // 필요한 제료
                // 레시피 내용
                String title = editTitle.getText().toString();
                String need = editFood.getText().toString();
                String recipe = editRecipe.getText().toString();
                String food = spinner.getSelectedItem().toString();

                //int img = recipeImg.


                if (title.length() > 0 && need.length() > 0) {
                    Intent intent = new Intent(Writing.this, MyRecipe.class);
                    intent.putExtra("name", title);
                    intent.putExtra("need", need);
                    intent.putExtra("recipe", recipe);
                    intent.putExtra("food", food);
                    System.out.println(title);
                    setResult(RESULT_OK, intent);
                    System.out.println("2222222");
                    Toast.makeText(Writing.this, "레시피 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                switch (food){
                    case "한식":
                        // 한식이면 한식 액티비티에도 리사이클러뷰 추가하기
                        break;
                }



            }
        });

        recipeImg = findViewById(R.id.food);
        recipeImg.setOnClickListener(new View.OnClickListener() {
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
            recipeImg.setImageURI(selectedImageUri);
        }
    }

}