package com.example.myeveryrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.Toast;

public class EggTimer extends AppCompatActivity {

    ImageView arrow;
    ImageView egg1;
    ImageView egg2;
    ImageView egg3;

    ProgressBar progressBar;

    EditText time_out_min,time_out_sec;

    Button btn_start,btn_reset;

    InputMethodManager imm;


    Handler handler=new Handler(){
        public void handleMessage(Message msg){
            String time = getTimeOut();


            //0초가 됐을때
            if(time.equals("00:00")){
                //타이머 초기화
                reset();

                //0초가 아니면
            }else{
                handler.sendEmptyMessage(0);
            }



        }
    };


    final static int INIT = 0;
    final static int RUN = 1;
    final static int PAUSE = 2;

    int cur_status = INIT;

    long baseTime;
    long pauseTime;
    long setTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_timer);

        arrow = findViewById(R.id.imageArrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        egg1 = findViewById(R.id.egg_1);
        egg2 = findViewById(R.id.egg_2);
        egg3 = findViewById(R.id.egg_3);

        egg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_out_min.setText("05");
                setTime();
            }
        });

        egg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_out_min.setText("07");
                setTime();
            }
        });

        egg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_out_min.setText("12");
                setTime();
            }
        });




        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        progressBar = findViewById(R.id.progressBar);


        time_out_min = findViewById(R.id.time_out_min);

        //time_out_min의 텍스트가 변할때 이벤트 발생
        time_out_min.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //EditText에 입력된 시간이 0초 이상일 경우 미리 시간 세팅
                if(time_out_min.hasFocus() && getEditTime() != 0){
                    setTime();
                    Log.d("ProgressTest","setTime = " + setTime);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        time_out_sec = findViewById(R.id.time_out_sec);

        //time_out_sec의 텍스트가 변할때 이벤트 발생
        time_out_sec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //EditText에 입력된 시간이 0초 이상일 경우 미리 시간 세팅
                if(time_out_sec.hasFocus() && getEditTime() != 0){
                    setTime();
                    Log.d("ProgressTest","setTime = " + setTime);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_start = findViewById(R.id.btn_start);
        btn_reset = findViewById(R.id.btn_reset);


        //시작 버튼 이벤트
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //시간이 0이 아닐 경우
                if(getEditTime() != 0){
                    //키보드 숨기기
                    hideKeyboard();

                    //start함수 참고
                    start(cur_status);

                    //시간이 0일 경우
                }else{

                    //시간을 입력하세요 토스트 띄우기
                    Toast.makeText(EggTimer.this, "시간을 입력하세요", Toast.LENGTH_SHORT).show();

                    //time_out_min에 포커스 주기
                    time_out_min.requestFocus();
                }
            }
        });



        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });



    }

    //타이머 초기화
    public void reset(){
        //핸들러 메세지 전달 종료
        handler.removeCallbacksAndMessages(null);

        //상태 변수 초기화
        cur_status = INIT;

        //long형으로 변환한 시간 초기화
        setTime = 0;

        //EditText 시간 초기화
        time_out_min.setText("00");
        time_out_min.setEnabled(true);
        time_out_sec.setText("00");
        time_out_sec.setEnabled(true);

        //시작 버튼 텍스트 변경
        btn_start.setText("시작");

        //초기화 버튼 비활성
        btn_reset.setEnabled(false);

        //프로그레스바 프로그레스 초기화
        progressBar.setProgress(0);
    }

    //키보드 숨기기
    public void hideKeyboard(){
        imm.hideSoftInputFromWindow(time_out_min.getWindowToken(),0);
        imm.hideSoftInputFromWindow(time_out_sec.getWindowToken(),0);
    }

    //현재 EditText에 입력된 시간을 가져와서 long형으로 파싱해서 리턴
    public long getEditTime(){
        long min = Long.parseLong(time_out_min.getText().toString()) * 60 * 1000;
        long sec = Long.parseLong(time_out_sec.getText().toString()) * 1000;
        return min + sec;
    }

    public void start(int status){

        switch (status) {
            //시작
            case INIT:
                baseTime = SystemClock.elapsedRealtime();
                btn_start.setText("멈춤");

                //초기화 버튼 비활성
                btn_reset.setEnabled(false);

                //EditText 비활성
                time_out_min.setEnabled(false);
                time_out_sec.setEnabled(false);

                //Handler로 메세지를 전달해서 타이머를 시작
                handler.sendEmptyMessage(0);
                cur_status = RUN;

                break;
            //멈춤
            case RUN:
                //Handler메세지 삭제
                handler.removeMessages(0);

                //멈춤 시간 기록
                pauseTime = SystemClock.elapsedRealtime();

                //시작 버튼 텍스트 변경
                btn_start.setText("재시작");

                //초기화 버튼 활성
                btn_reset.setEnabled(true);

                //타이머 상태 변수 변경
                cur_status = PAUSE;
                break;
            //재시작
            case PAUSE:
                //현재 시간 다시 기록
                long now = SystemClock.elapsedRealtime();



                //타이머 시간 세팅
                baseTime += (now - pauseTime);

                //시작 버튼 텍스트 변경
                btn_start.setText("멈춤");

                //초기화 버튼 비활성
                btn_reset.setEnabled(false);


                //타이머 재시작
                handler.sendEmptyMessage(0);

                //타이머 상태 변수 변경
                cur_status = RUN;
                break;
        }

    }


    //핸들러 안에서 EditText의 시간을 return해주고 프로그레스바의 프로그레스를 세팅
    public String getTimeOut(){
        long now = SystemClock.elapsedRealtime();
        long outTime = baseTime - now + setTime;

        long sec = outTime/1000%60;
        long min = outTime/1000/60;


        //0.1초 단위가 남아있을때 초가 넘어가서 0.5초에도 0초로 표시 되기 때문에
        //0.1초 단위를 계산해서 초가 60초 이하일때 0.1초 단위가 남아 있으면
        // 초가 변경되지 않도록 세팅
        if(outTime%1000/10 != 0 && sec < 60){
            sec += 1;
            if(sec == 60){
                sec = 0;
                min += 1;
            }
        }

        String easy_outTime = String.format("%02d:%02d",min,sec);
        String[] times = easy_outTime.split(":");

        time_out_min.setText(times[0]);
        time_out_sec.setText(times[1]);

        progressBar.setProgress((int)((now-baseTime)+(setTime/1000)));
        return easy_outTime;
    }

    //EditText에 입력된 시간을 long형 변수에 세팅, 프로그레스바 최대치 세팅
    public void setTime(){
        setTime = Long.parseLong(time_out_min.getText().toString()) * 1000 * 60 +
                Long.parseLong(time_out_sec.getText().toString()) * 1000;
        progressBar.setMax((int)setTime);
    }

}