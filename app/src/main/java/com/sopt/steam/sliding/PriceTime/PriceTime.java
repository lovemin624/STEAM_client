package com.sopt.steam.sliding.PriceTime;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sopt.steam.R;

import java.util.ArrayList;
import java.util.List;


public class PriceTime extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public static PriceTime newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PriceTime fragment = new PriceTime();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_price_time, container, false);
        View header = getActivity().getLayoutInflater().inflate(R.layout.activity_price_time_header, null, false);

        ListView listview = (ListView)view.findViewById(R.id.listview);

        List<PriceTimeItem> item = new ArrayList<PriceTimeItem>();

        listview.addHeaderView(header);

        item.add(new PriceTimeItem("구분", "종류"));
        item.add(new PriceTimeItem("구분", "종류"));
        item.add(new PriceTimeItem("구분", "종류"));

        PriceTimeAdapter adapter = new PriceTimeAdapter(getActivity().getApplicationContext(),item);
        listview.setAdapter(adapter);

        return view;
    }
}


class PriceTimeAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<PriceTimeItem> arSrc;

    //생성자
    public PriceTimeAdapter(Context context, List<PriceTimeItem> arItem){
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
    public PriceTimeItem getItem(int position) {
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

            convertView = mInflater.inflate(R.layout.activity_price_time_row, parent, false);

            holder.type = (TextView)convertView.findViewById(R.id.type);
            holder.price = (TextView)convertView.findViewById(R.id.price);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.type.setText(arSrc.get(position).getType());
        holder.price.setText(arSrc.get(position).getPrice());

        return convertView;
    }

    private class ViewHolder{
        public TextView type;
        public TextView price;
    }

}