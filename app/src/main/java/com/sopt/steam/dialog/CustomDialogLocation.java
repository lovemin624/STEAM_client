package com.sopt.steam.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sopt.steam.R;


/**
 * Created by KyoungHyun on 16. 5. 1..
 */
public class CustomDialogLocation extends Dialog {

    private Button searchCurrent;
    private Button searchSend;

    private View.OnClickListener searchCurrentEvent;
    private View.OnClickListener sendLocationEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_location);

        searchCurrent = (Button) findViewById(R.id.searchCurrent);
        searchSend = (Button)findViewById(R.id.sendLocation);


        searchCurrent.setOnClickListener(searchCurrentEvent);
        searchSend.setOnClickListener(sendLocationEvent);


    }

    public CustomDialogLocation(Context context,View.OnClickListener CurrentEvent,View.OnClickListener BtnEvent) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);

        this.searchCurrentEvent = CurrentEvent;
        this.sendLocationEvent = BtnEvent;

    }


}

