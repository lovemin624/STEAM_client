package com.sopt.steam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by parkkyounghyun on 2016. 6. 28..
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mName;
        public TextView mLocation;
        public TextView mPrice;
        public TextView mStar;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.image);
            mName = (TextView)view.findViewById(R.id.RoomName);
            mLocation = (TextView)view.findViewById(R.id.RoomLocation);
            mPrice = (TextView)view.findViewById(R.id.RoomPrice);
            mStar = (TextView)view.findViewById(R.id.RoomStar);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<MyData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mName.setText(mDataset.get(position).name);
        holder.mLocation.setText(mDataset.get(position).location);
        holder.mPrice.setText(mDataset.get(position).price);
        holder.mStar.setText(mDataset.get(position).star);
        holder.mImageView.setImageResource(mDataset.get(position).img);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


