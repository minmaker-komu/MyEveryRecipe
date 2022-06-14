package com.example.myeveryrecipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MaterialAdapter extends BaseAdapter {

    ProgressBar pb;
    static Handler handler;
    int cnt = 0;
    String dueTime;

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MaterialData> materialData;

    public MaterialAdapter(Context context, ArrayList<MaterialData> data) {
        mContext = context;
        materialData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return materialData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MaterialData getItem(int position) {
        return materialData.get(position);
    }

    public View getView(int position, View converView, ViewGroup parent){

        View view = mLayoutInflater.inflate(R.layout.material_listview_item, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.material_image);
        TextView materialName = (TextView)view.findViewById(R.id.material_name);
        TextView materialDate = (TextView)view.findViewById(R.id.material_buydate);
        ProgressBar materialProgress = view.findViewById(R.id.pb);
        //TextView materialDue = (TextView)view.findViewById(R.id.material_due);
        TextView materialNum = (TextView)view.findViewById(R.id.material_num);

        imageView.setImageResource(materialData.get(position).getMaterial_image());
        materialName.setText(materialData.get(position).getMaterial_name());
        materialDate.setText(materialData.get(position).getMaterial_date());
        //materialDue.setText(materialData.get(position).getMaterial_due());
        dueTime = materialData.get(position).getMaterial_due();
        materialProgress.setMax(100);

        materialNum.setText(materialData.get(position).getMaterial_num());


        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                //String time = getTimeOut();
                if(cnt<100){
                    cnt++;
                    pb.setProgress(cnt);
                    MaterialAdapter.this.sendMessage();
                }else{
                    handler.removeCallbacksAndMessages(null);
                }
                int sec = (msg.arg1/100) % 60;
                int min = (msg.arg1/100) / 60 % 60;
                int hour = (msg.arg1/100) / 3600 % 24;
                int day = (msg.arg2 / 100) / 86400;

            }
        };



        return view;
    }

    public void sendMessage() {
        Message message = new Message();
        //0.01초에 한번씩 Handler로 메세지를 전송
        handler.sendMessageDelayed(message,10);
    }

    /*public String getTimeOut() {
        long now = SystemClock.elapsedRealtime();
        //int position = adapterview.getAdapter().getItem();
        //long DueTime = materialData.get(position).getMaterial_due();
        long outTime = DueTime - now;
    }*/

}
