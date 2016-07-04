package com.sopt.steam.sliding.notice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sopt.steam.R;


public class notice extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public static notice newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        notice fragment = new notice();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notice, container, false);

        return view;
    }
}
