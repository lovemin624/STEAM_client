package com.sopt.steam.sliding.information;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sopt.steam.R;

import java.util.ArrayList;
import java.util.List;


public class information extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public static information newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        information fragment = new information();
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
        View view = inflater.inflate(R.layout.activity_information, container, false);

        List<informationItem> item = new ArrayList<informationItem>();
        final ListView listview = (ListView)view.findViewById(R.id.listview);

        item.add(new informationItem(null,"a0", "b0", "c0", R.drawable.img1));
        item.add(new informationItem(null,"a1","b2","c1", R.drawable.img2));
        item.add(new informationItem(null,"a2","b3","c2", R.drawable.img3));

        InformationAdapter adapter = new InformationAdapter(getActivity().getApplicationContext(),item);
        listview.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listview);


        return view;
    }

    //스크롤뷰 안에 리스트뷰 스크롤 없애는 메소드

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)  return;

        int numberOfItems = listAdapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() *  (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);

    }

}


class InformationAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<informationItem> arSrc;

    //생성자
    public InformationAdapter(Context context, List<informationItem> arItem){
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
    public informationItem getItem(int position) {
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

            convertView = mInflater.inflate(R.layout.listview_room_detail_information, parent, false);

            holder.iv_imageView = (ImageView)convertView.findViewById(R.id.iv_imageview);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_number = (TextView)convertView.findViewById(R.id.tv_number);
            holder.tv_option = (TextView)convertView.findViewById(R.id.tv_option);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.iv_imageView.setImageResource(arSrc.get(position).getTempimage());
        holder.tv_name.setText(arSrc.get(position).getTv_name());
        holder.tv_number.setText(arSrc.get(position).getTv_number());
        holder.tv_option.setText(arSrc.get(position).getTv_option());

        return convertView;
    }

    private class ViewHolder{
        public ImageView iv_imageView;
        public TextView tv_name;
        public TextView tv_number;
        public TextView tv_option;
    }

}