package com.sopt.steam.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendSucceed() {
        Toast.makeText(this, "전송완료!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void sendFailure() {
        Toast.makeText(this, "전송실패!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void networkError() {
        ErrorController errorController = new ErrorController(this.getApplicationContext());
        errorController.notifyNetworkError();
    }


}
