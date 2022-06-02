package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Join extends AppCompatActivity {

    Button JoinComplete;
    EditText Name;
    EditText ID_insert;
    EditText PW_insert;

    private Context mContext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        mContext = this;

        // 정의
        Name = findViewById(R.id.NAME);
        ID_insert = findViewById(R.id.ID_insert);
        PW_insert = findViewById(R.id.PW_insert);

        //getSharedPreferences("파일이름",'모드')
        //모드 => 0 (읽기,쓰기가능)
        //모드 => MODE_PRIVATE (이 앱에서만 사용가능)
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        // 회원가입 완료 버튼
        JoinComplete = findViewById(R.id.button_join_Complete);
        JoinComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("Myinfo",MODE_PRIVATE);
                String name = Name.getText().toString();
                String id = ID_insert.getText().toString();
                String pw = PW_insert.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("id",id);
                editor.putString("pw",pw);
                editor.commit();

//                PreferenceManager.setString(mContext, "name", Name.getText().toString()); //id라는 키값으로 저장
//                PreferenceManager.setString(mContext, "id", ID_insert.getText().toString()); //id라는 키값으로 저장
//                PreferenceManager.setString(mContext, "pw", PW_insert.getText().toString()); //pw라는 키값으로 저장

                // 로그인 창으로 이동
                Intent intent = new Intent(getApplicationContext(),Login.class);

                /*// 저장한 키 값으로 저장된 아이디와 암호를 불러와 String 값에 저장
                String name = PreferenceManager.getString(mContext, "name");
                String id = PreferenceManager.getString(mContext, "id");
                String pw = PreferenceManager.getString(mContext, "pw");*/

                //아이디와 암호가 비어있는 경우를 체크
                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pw) || TextUtils.isEmpty(name)){
                    //아이디나 암호 둘 중 하나가 비어있으면 토스트메시지를 띄운다
                    Toast.makeText(Join.this, "이름/아이디/비밀번호를 입력해주세요",
                            Toast.LENGTH_SHORT).show();
                }else { //둘 다 충족하면 다음 동작을 구현해놓음
                    Toast.makeText(Join.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    intent.putExtra("name",name);
                    intent.putExtra("id",id);
                    intent.putExtra("pw",pw);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}