package com.sopt.steam.sliding.reviewList;

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



public class ReviewList extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    public static ReviewList newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ReviewList fragment = new ReviewList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_information, container, false);

        List<ReviewListItem> item = new ArrayList<ReviewListItem>();
        final ListView listview = (ListView)view.findViewById(R.id.listview);

        item.add(new ReviewListItem("이름", "날짜", "스터디룸", "내용"));
        item.add(new ReviewListItem("이름", "날짜", "스터디룸", "내용"));
        item.add(new ReviewListItem("이름", "날짜", "스터디룸", "내용"));

        ReviewListAdapter adapter = new ReviewListAdapter(getActivity().getApplicationContext(),item);
        listview.setAdapter(adapter);

        return view;
    }
}


class ReviewListAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<ReviewListItem> arSrc;

    //생성자
    public ReviewListAdapter(Context context, List<ReviewListItem> arItem){
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
    public ReviewListItem getItem(int position) {
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

            convertView = mInflater.inflate(R.layout.listview_review, parent, false);

            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.date = (TextView)convertView.findViewById(R.id.date);
            holder.room = (TextView)convertView.findViewById(R.id.room);
            holder.content = (TextView)convertView.findViewById(R.id.content);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText(arSrc.get(position).getName());
        holder.date.setText(arSrc.get(position).getDate());
        holder.room.setText(arSrc.get(position).getRoom());
        holder.content.setText(arSrc.get(position).getContent());

        return convertView;
    }

    private class ViewHolder{
        public TextView name;
        public TextView date;
        public TextView room;
        public TextView content;
    }

}