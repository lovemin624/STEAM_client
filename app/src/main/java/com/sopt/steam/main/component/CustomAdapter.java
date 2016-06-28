package com.sopt.steam.main.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sopt.steam.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-23.
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> itemDatas = null;
    private LayoutInflater layoutInflater = null;

    public CustomAdapter(ArrayList<ListViewItem> itemDatas, Context ctx) {  //생성자
        this.itemDatas = itemDatas;
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItemDatas(ArrayList<ListViewItem> itemDatas) {
        this.itemDatas = itemDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemDatas != null ? itemDatas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return (itemDatas != null && (position >= 0 && position < itemDatas.size()) ? itemDatas.get(position):null);
    }

    @Override
    public long getItemId(int position) {
        return (itemDatas != null && ( position>=0 && position < itemDatas.size()) ? position:0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.list_title);
        TextView date = (TextView)convertView.findViewById(R.id.list_date);

        ListViewItem itemData_temp = itemDatas.get(position);

        title.setText(itemData_temp.title_data);
        date.setText(itemData_temp.date_data);

        return convertView;
    }
}
