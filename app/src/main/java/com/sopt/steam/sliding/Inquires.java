package com.sopt.steam.sliding;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.sopt.steam.R;


/**
 * Created by 즤 on 2016-06-29.
 */

public class Inquires extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Dialog getDialog;

    static Inquires newInstance() {
        Inquires f = new Inquires();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        //args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.inquires_layout, container, false);
        getDialog = this.getDialog();
        getDialog.requestWindowFeature(STYLE_NO_TITLE);
        // View tv = v.findViewById(R.id.text);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //배경 투명하게 만듦

        WindowManager.LayoutParams params = getDialog().getWindow()
                .getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        getDialog().getWindow().setAttributes(params);

        Button btn1 = (Button) v.findViewById(R.id.callButton);
        Button btn2 = (Button) v.findViewById(R.id.websiteButton);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
                startActivity(intent);

                //finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(intent);

                //finish();
                }
            });

        return v;
    }

}
