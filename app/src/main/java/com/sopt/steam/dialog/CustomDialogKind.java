package com.sopt.steam.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.sopt.steam.R;

import java.util.ArrayList;



/**
 * Created by KyoungHyun on 16. 5. 1..
 */
public class CustomDialogKind extends Dialog{

    private CheckBox allChk;
    //왜 객체로 안될까..?
    private CheckBox kindChkbox1;
    private CheckBox kindChkbox2;
    private CheckBox kindChkbox3;
    private CheckBox kindChkbox4;
    private CheckBox kindChkbox5;
    private CheckBox kindChkbox6;

    private Button sendKind;
    private View.OnClickListener event;

    private ArrayList<String> ChkName;
    private ArrayList<String> Chkitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_kind);

        allChk = (CheckBox)findViewById(R.id.totalKindChk);
        kindChkbox1 = (CheckBox)findViewById(R.id.chkbox1);
        kindChkbox2 = (CheckBox)findViewById(R.id.chkbox2);
        kindChkbox3 = (CheckBox)findViewById(R.id.chkbox3);
        kindChkbox4 = (CheckBox)findViewById(R.id.chkbox4);
        kindChkbox5 = (CheckBox)findViewById(R.id.chkbox5);
        kindChkbox6 = (CheckBox)findViewById(R.id.chkbox6);

        ChkName = new ArrayList<String>();
        ChkName.add("#카페");
        ChkName.add("#스터디룸");
        ChkName.add("#세미나실");
        ChkName.add("#연습실");
        ChkName.add("#다목적실");
        ChkName.add("#회의실");

        sendKind = (Button)findViewById(R.id.sendKind);
        sendKind.setOnClickListener(event);

        allChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allChk.isChecked()){
                    kindChkbox1.setChecked(true);
                    kindChkbox2.setChecked(true);
                    kindChkbox3.setChecked(true);
                    kindChkbox4.setChecked(true);
                    kindChkbox5.setChecked(true);
                    kindChkbox6.setChecked(true);

                }
                else{
                    kindChkbox1.setChecked(false);
                    kindChkbox2.setChecked(false);
                    kindChkbox3.setChecked(false);
                    kindChkbox4.setChecked(false);
                    kindChkbox5.setChecked(false);
                    kindChkbox6.setChecked(false);
                }
            }
        });

        kindChkbox1.setOnClickListener(chkState);
        kindChkbox2.setOnClickListener(chkState);
        kindChkbox3.setOnClickListener(chkState);
        kindChkbox4.setOnClickListener(chkState);
        kindChkbox5.setOnClickListener(chkState);
        kindChkbox6.setOnClickListener(chkState);


        if(Chkitem == null)
            ChkInit();
        else{
            if(Chkitem.get(0)=="all")
                ChkInit();
            else
                ChkFind();

//            Log.i("test",String.valueOf(Chkitem.get(0)));

        }


    }



    public CustomDialogKind(Context context ,ArrayList item, View.OnClickListener BtnEvent) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);

        Chkitem = item;
        this.event = BtnEvent;

    }

    private void ChkInit(){
        allChk.setChecked(true);
        kindChkbox1.setChecked(true);
        kindChkbox2.setChecked(true);
        kindChkbox3.setChecked(true);
        kindChkbox4.setChecked(true);
        kindChkbox5.setChecked(true);
        kindChkbox6.setChecked(true);
    }


    private void ChkFind(){

        for(int i = 0; i < Chkitem.size(); i++) { // 현재 선택된 정보

            for (int j = 0; j < ChkName.size(); j++) { // 카테고리 태그

                if (ChkName.get(j).equals( Chkitem.get(i))) {

                    if (j == 0) {
                        kindChkbox1.setChecked(true);
                        break;
                    }
                    if (j == 1) {
                        kindChkbox2.setChecked(true);
                        break;
                    }
                    if (j == 2) {
                        kindChkbox3.setChecked(true);
                        break;
                    }
                    if (j == 3) {
                        kindChkbox4.setChecked(true);
                        break;
                    }
                    if (j == 4) {
                        kindChkbox5.setChecked(true);
                        break;
                    }
                    if (j == 5) {
                        kindChkbox6.setChecked(true);
                        break;
                    }

                }
            }
        }
    }
    private View.OnClickListener chkState = new View.OnClickListener() {
        public void onClick(View v) {

            if(kindChkbox1.isChecked() && kindChkbox2.isChecked() && kindChkbox3.isChecked() &&
                    kindChkbox4.isChecked() && kindChkbox5.isChecked() && kindChkbox6.isChecked()){
                allChk.setChecked(true);
            }
            else
                allChk.setChecked(false);
        }
    };

    public ArrayList CheckKind(){
        ArrayList<String> checkValue = new ArrayList<String>();

        if(allChk.isChecked()){
            checkValue.add("all");
        }
        else{
            if(kindChkbox1.isChecked())
                checkValue.add(kindChkbox1.getText().toString());
            if(kindChkbox2.isChecked())
                checkValue.add(kindChkbox2.getText().toString());
            if(kindChkbox3.isChecked())
                checkValue.add(kindChkbox3.getText().toString());
            if(kindChkbox4.isChecked())
                checkValue.add(kindChkbox4.getText().toString());
            if(kindChkbox5.isChecked())
                checkValue.add(kindChkbox5.getText().toString());
            if(kindChkbox6.isChecked())
                checkValue.add(kindChkbox6.getText().toString());
        }

        return checkValue;
    }

}
