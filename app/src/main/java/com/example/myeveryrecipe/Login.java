package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText ID;
    EditText PW;
    EditText Name;


    Button login;
    Button join;

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;


        // 이름 아이디 비밀번호 받기
        // 이름 받기
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        System.out.println(name);
        // 아이디 받기
        String id = intent.getStringExtra("id");
        ID = findViewById(R.id.ID);
        ID.setText(id);
        setResult(RESULT_OK,intent);
        // 비밀번호 받기
        String pw =intent.getStringExtra("pw");
        PW = findViewById(R.id.PW);
        PW.setText(pw);

        // 회원가입 버튼 눌렀을 때 회원가입창으로 이동
        join = findViewById(R.id.button_join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Join.class);
                startActivity(intent);
                finish();
            }
        });


        // 로그인 버튼 눌렀을 때 메인으로 가기
        login = findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Toast.makeText(Login.this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                //intent.putExtra("name",name);
                startActivity(intent);*/

                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pw)){
                    //아이디나 암호 둘 중 하나가 비어있으면 토스트메시지를 띄운다
                    Toast.makeText(Login.this, "아이디/암호를 입력해주세요",
                            Toast.LENGTH_SHORT).show();
                }else { //둘 다 충족하면 다음 동작을 구현해놓음
                    Toast.makeText(Login.this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    //intent.putExtra("id",id);
                    //intent.putExtra("pw",pw);
                    //intent.putExtra("name",name);
                    startActivity(intent);
                }
            }
        });
    }
}