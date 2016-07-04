package com.sopt.steam.afterFilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sopt.steam.R;

import java.util.List;



/**
 * Created by parkkyounghyun on 2016. 7. 2..
 */
public class FilterAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<ListItem> arSrc;

    //생성자
    public FilterAdapter(Context context, List<ListItem> arItem){
        //인플레이트 준비를 합니다.
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc = arItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arSrc.size();
    }

    @Override
    public ListItem getItem(int position) {
        // TODO Auto-generated method stub
        return arSrc.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        // TODO Auto-generated method stub

        if(convertView == null){
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.listview_filter, parent, false);

            holder.iv_imageView = (ImageView)convertView.findViewById(R.id.iv_imageView);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.btn_time = (Button)convertView.findViewById(R.id.btn_time);
            holder.btn_price = (Button)convertView.findViewById(R.id.btn_price);
            holder.tv_grade = (TextView)convertView.findViewById(R.id.tv_grade);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.iv_imageView.setImageResource(arSrc.get(position).getTempImage());
        holder.tv_name.setText(arSrc.get(position).getName());
        holder.btn_time.setText(arSrc.get(position).getName());
        holder.btn_price.setText(arSrc.get(position).getName());
        holder.tv_grade.setText(arSrc.get(position).getGrade());

        return convertView;
    }

    private class ViewHolder{
        public ImageView iv_imageView;
        public TextView tv_name;
        public Button btn_time;
        public Button btn_price;
        public TextView tv_grade;
    }
}
