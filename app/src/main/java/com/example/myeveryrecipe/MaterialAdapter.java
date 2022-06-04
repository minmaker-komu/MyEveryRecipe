package com.example.myeveryrecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MaterialAdapter extends BaseAdapter {

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
        TextView materialDue = (TextView)view.findViewById(R.id.material_due);
        TextView materialNum = (TextView)view.findViewById(R.id.material_num);

        imageView.setImageResource(materialData.get(position).getMaterial_image());
        materialName.setText(materialData.get(position).getMaterial_name());
        materialDate.setText(materialData.get(position).getMaterial_date());
        materialDue.setText(materialData.get(position).getMaterial_due());
        materialNum.setText(materialData.get(position).getMaterial_num());

        return view;
    }

}
