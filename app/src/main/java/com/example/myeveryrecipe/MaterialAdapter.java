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
import java.util.Calendar;

public class MaterialAdapter extends BaseAdapter {

    ProgressBar pb;
    Handler handler;
    int cnt = 0;
    String dueTime;

    String[] dday;

    String year;
    String month;
    String mday;

    long count;
    long day;
    long tday;

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MaterialData> materialData;

    // Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

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
        pb = view.findViewById(R.id.pb);
        TextView materialDday = (TextView)view.findViewById(R.id.material_dday);
        TextView materialNum = (TextView)view.findViewById(R.id.material_num);
        dueTime = materialData.get(position).getMaterial_due();
        dday = dueTime.split("\\.");
        System.out.println(dueTime+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //System.out.println(dday[0]+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for(int i=0 ; i<dday.length ; i++)
        {
            System.out.println("date["+i+"] : "+dday[i]);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
        }
        year = dday[0];
        month = dday[1];
        mday = dday[2];
        int mYear = Integer.parseInt(year);
        int mMonthOfYear = Integer.parseInt(month);
        int mDayOfMonth = Integer.parseInt(mday);

        imageView.setImageResource(materialData.get(position).getMaterial_image());
        materialName.setText(materialData.get(position).getMaterial_name());
        materialDate.setText(materialData.get(position).getMaterial_date());
        materialDday.setText(getDday(mYear,mMonthOfYear,mDayOfMonth));
        //materialDday.setText(ddayResult_int(mYear,mMonthOfYear,mDayOfMonth));
        //materialDday.setText("EPTMXM");

        pb.setMax(100);

        materialNum.setText(materialData.get(position).getMaterial_num());


        // 신선도 표시
        long count1 = day-tday;

        if (count1 > 0){
            int i =0;
            if(count1 <=3){
                i=30;
                pb.setProgress(i);
            }
            else if(count1 <=7) {
                i = 60;
                pb.setProgress(i);
            }
            else {
                i=100;
                pb.setProgress(i);
            }
        } else if (count1 == 0) {
            pb.setProgress(20);
        } else {
            pb.setProgress(5);
        }






        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {

                if(cnt<100){
                    //cnt = 20;
                    pb.incrementProgressBy(-20);
                    MaterialAdapter.this.sendMessage();

                }else{
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        return view;
    }

    public void sendMessage() {
        Message message = new Message();
        //1초에 한번씩 Handler로 메세지를 전송
        handler.sendMessageDelayed(message,1000);
    }


    // 디데이 반환

    private String getDday(int dateEndY, int dateEndM, int dateEndD) {
        // D-day 설정
        /*dday = dueTime.split("\\.");
        year = dday[0];
        month = dday[1];
        day = dday[2];
        mYear = Integer.parseInt(year);
        mMonthOfYear = Integer.parseInt(month);
        mDayOfMonth = Integer.parseInt(day);

        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(mYear, mMonthOfYear, mDayOfMonth);

        // D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;*/

        Calendar today = Calendar.getInstance(); //현재 오늘 날짜
        Calendar dday2 = Calendar.getInstance();

        //시작일, 종료일 데이터 저장
        Calendar calendar = Calendar.getInstance();
        int cyear = calendar.get(Calendar.YEAR);
        int cmonth = (calendar.get(Calendar.MONTH) + 1);
        int cday = calendar.get(Calendar.DAY_OF_MONTH);

        today.set(cyear, cmonth, cday);

        dday = dueTime.split("\\.");
        year = dday[0];
        month = dday[1];
        mday = dday[2];
        dateEndY = Integer.parseInt(year);
        dateEndM = Integer.parseInt(month);
        dateEndD = Integer.parseInt(mday);

        dday2.set(dateEndY, dateEndM, dateEndD);// D-day의 날짜를 입력합니다.

        day = dday2.getTimeInMillis() / 86400000;
        // 각각 날의 시간 값을 얻어온 다음
        //( 1일의 값(86400000 = 24시간 * 60분 * 60초 * 1000(1초값) ) )

        tday = today.getTimeInMillis() / 86400000;
        //long count = tday - day; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
        count = day - tday; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.


        // 출력 시 d-day 에 맞게 표시
        String strFormat;
        if (count > 0) {
            strFormat = "D-%d";
        } else if (count == 0) {
            strFormat = "Today";
        } else {
            count *= -1;
            strFormat = "D+%d";
        }

        final String strCount = (String.format(strFormat, count));
        return strCount;

    }

    // 디데이값 계산

    public int onCalculatorDate (int dateEndY, int dateEndM, int dateEndD) {
        try {
            Calendar today = Calendar.getInstance(); //현재 오늘 날짜
            Calendar dday2 = Calendar.getInstance();

            //시작일, 종료일 데이터 저장
            Calendar calendar = Calendar.getInstance();
            int cyear = calendar.get(Calendar.YEAR);
            int cmonth = (calendar.get(Calendar.MONTH) + 1);
            int cday = calendar.get(Calendar.DAY_OF_MONTH);

            today.set(cyear, cmonth, cday);

            dday = dueTime.split("\\.");
            year = dday[0];
            month = dday[1];
            mday = dday[2];
            dateEndY = Integer.parseInt(year);
            dateEndM = Integer.parseInt(month);
            dateEndD = Integer.parseInt(mday);

            dday2.set(dateEndY, dateEndM, dateEndD);// D-day의 날짜를 입력합니다.

            long day = dday2.getTimeInMillis() / 86400000;
            // 각각 날의 시간 값을 얻어온 다음
            //( 1일의 값(86400000 = 24시간 * 60분 * 60초 * 1000(1초값) ) )

            long tday = today.getTimeInMillis() / 86400000;
            long count = tday - day; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            return (int) count; // 날짜는 하루 + 시켜줘야합니다.
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 디데이 결과 출력
    public int ddayResult_int(int dateEndY, int dateEndM, int dateEndD) {
        int result = 0;
        result = onCalculatorDate(dateEndY, dateEndM, dateEndD);
        return result;
    }

}
