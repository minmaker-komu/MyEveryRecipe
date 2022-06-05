package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadMyRecipe extends AppCompatActivity {

    SharedPreferences pref; //프리퍼런스
    SharedPreferences.Editor editor; //에디터

    ImageView arrow;
    EditText recipe_title;
    EditText recipe_need;
    EditText recipe_context;
    ImageView recipe_img;
    TextView edit_text;

    private final int GET_GALLERY_IMAGE = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_my_recipe);

        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                startActivity(intent);
            }
        });

        //pref = getSharedPreferences("")




        // 레시피 제목 수정
        edit_text = findViewById(R.id.edit_text);
        edit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_update = recipe_title.getText().toString();
                String need_update = recipe_need.getText().toString();
                String context_update = recipe_context.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                intent.putExtra("edit", true);
                intent.putExtra("name_update",name_update);
                intent.putExtra("need_update",need_update);
                intent.putExtra("context_update",context_update);
                intent.putExtra("position", getIntent().getIntExtra("position", -1));
                System.out.println("*****");
                startActivity(intent);
                finish();

                /*Intent intent = new Intent();
                System.out.println("7777777");
                intent.putExtra("name_update",name_update);
                intent.putExtra("need_update",need_update);
                intent.putExtra("context_update",context_update);
                setResult(609,intent);
                System.out.println("@2222224444");
                finish();*/


            }
        });

        // 앍어오기
        recipe_title = findViewById(R.id.recipe_show);
        recipe_img = findViewById(R.id.recipe_img_show);
        recipe_need = findViewById(R.id.editFOOD);
        recipe_context = findViewById(R.id.editRecipe);
        String title = getIntent().getStringExtra("title");
        //String food = getIntent().getStringExtra("title");
        String need = getIntent().getStringExtra("need");
        String recipe = getIntent().getStringExtra("recipe");
        int img = getIntent().getIntExtra("img1",0);
        recipe_title.setText(title);
        recipe_img.setImageResource(img);
        recipe_need.setText(need);
        recipe_context.setText(recipe);

        recipe_img.setOnClickListener(new View.OnClickListener() {
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
            recipe_img.setImageURI(selectedImageUri);
        }
    }


}