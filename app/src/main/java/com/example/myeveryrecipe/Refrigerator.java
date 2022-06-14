package com.example.myeveryrecipe;




import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Refrigerator extends AppCompatActivity {
    TextView date;

    // 메뉴 선택 이미지
    ImageView cook;
    ImageView refri;
    ImageView recipe;
    ImageView mypage;
    MaterialAdapter materialAdapter;

    FloatingActionButton material_add;

    ArrayList<MaterialData> materialData;

    BackgroundThread backgroundThread;

    Gson gson;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

        sharedPreferences = getSharedPreferences("my_material",MODE_PRIVATE);


        // 정의
        cook = findViewById(R.id.menu_cook);
        refri = findViewById(R.id.menu_refri);
        recipe = findViewById(R.id.menu_writing);
        mypage = findViewById(R.id.menu_profile);

        material_add = findViewById(R.id.refrigerator);
        material_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Material.class);
                startActivityForResult(intent,99);
            }
        });

        // 홈
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        // 냉장고
        refri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Refrigerator.class);
                startActivity(intent);
            }
        });
        // 내 레시피
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyRecipe.class);
                startActivity(intent);
            }
        });
        // 마이 페이지
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyPage.class);
                startActivity(intent);
            }
        });

        // 현재 날짜
        date = findViewById(R.id.textDate);
        //date.setText(getTime());

        // 초기에 보여질 재료들
        materialData = new ArrayList<MaterialData>();

        materialData.add(new MaterialData(R.drawable.bread, "빵","22.05.06","23.09.11","2"));

        readData();

        // 리스트뷰 보이게
        ListView listView = (ListView)findViewById(R.id.listview);
        materialAdapter = new MaterialAdapter(this,materialData);

        listView.setAdapter(materialAdapter);

        // 재료 확인하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //materialAdapter.getItem(position).getMaterial_name();
                int itemPosition = listView.getCheckedItemPosition();

                MaterialData material = materialData.get(itemPosition+1);
                Intent intent = new Intent(getApplicationContext(), ReadMaterial.class);
                intent.putExtra("material_name", material.getMaterial_name());
                intent.putExtra("material_date", material.getMaterial_date());
                intent.putExtra("material_due", material.getMaterial_due());
                intent.putExtra("material_num", material.getMaterial_num());
                intent.putExtra("material_img", material.getMaterial_image());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        // 길게 클릭했을 때 삭제하기
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Refrigerator.this);
                builder.setTitle("재료 삭제하기")
                        .setMessage(materialData.get(position).getMaterial_name() + "을(를) 삭제하시겠습니까?")
                        .setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                materialData.remove(position);
                                materialAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNeutralButton("취소", null).show();
                return true;
            }
        });
    }

    public void InitializeMaterialData()
    {
        materialData = new ArrayList<MaterialData>();

        materialData.add(new MaterialData(R.drawable.bread, "빵","22.05.06","23.09.11","2"));
    }

    private String getTime() { long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = dateFormat.format(date); return getTime;
    }

    @Override
    protected void onStart() {
        super.onStart();
        int action = 0;
        //if (getIntent().getBooleanExtra("new", false)) action = 1;
        if (getIntent().getBooleanExtra("edit", false)) action = 2;
        if (action > 0) {
            System.out.println("^^^^^");
            String name_update = getIntent().getStringExtra("name_update");
            String date_update = getIntent().getStringExtra("date_update");
            String date2_update = getIntent().getStringExtra("date2_update");
            System.out.println(name_update);
            int position = getIntent().getIntExtra("position", -1);
            System.out.println(position);
            System.out.println("%%%");

            if (position != -1) {
                System.out.println("####");
                materialData.get(position).setMaterial_name(name_update);
                materialData.get(position).setMaterial_date(date_update);
                materialData.get(position).setMaterial_due(date2_update);
            }
            materialAdapter.notifyDataSetChanged();

        }

        backgroundThread = new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);
        if(resultCode == 800){
            System.out.println("@@@@@");
            String name = data.getStringExtra("mname");
            String date = data.getStringExtra("date");
            String date2 = data.getStringExtra("date2");

            materialData.add(new MaterialData(R.drawable.material_pic,name,date,date2,"1"));
            materialAdapter.notifyDataSetChanged();
            //mAdapter.notifyItemChanged();
            //image = getIntent().getIntExtra("image",0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("****stop 되었습니다****");
        saveData(materialData);

        boolean retry = true;
        backgroundThread.setRunning(false);

        while(retry){
            try{
                backgroundThread.join();
                retry = false;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    public void saveData(ArrayList<MaterialData> mList){
        sharedPreferences = getSharedPreferences("my_material",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String json = gson.toJson(mList);

        editor.putString("material_list",json);
        editor.apply();

    }

    public ArrayList<MaterialData> readData(){

        SharedPreferences sharedPreferences = getSharedPreferences("my_material",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("material_list","");
        Type type = new TypeToken<ArrayList<MaterialData>>(){
        }.getType();
        materialData = gson.fromJson(json,type);

        if(materialData == null){
            materialData = new ArrayList<>();
        }
        return materialData;
    }

    private final MyHandler myHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private final WeakReference<Refrigerator> mActivity;
        public MyHandler(Refrigerator activity) {
            mActivity = new WeakReference<Refrigerator>(activity);
        }

        @Override
        public void handleMessage(Message msg){
            Refrigerator activity = mActivity.get();
            if (activity != null){

                activity.handleMessage(msg);
            }
        }
    }

    public void handleMessage(Message msg){
        date.setText(getTime());
        //date.setText(DateFormat.getDateInstance().format(new Date()));
    }

    public class BackgroundThread extends Thread{
        boolean running = false;
        void setRunning(boolean b) {
            running = b;
        }

        @Override
        public void run(){
            while (running){
                try {
                    sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                myHandler.sendMessage(myHandler.obtainMessage());
            }
        }
    }
}