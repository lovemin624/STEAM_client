package com.sopt.steam.splash.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;
import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.main.view.MainActivity;
import com.sopt.steam.splash.presenter.SplashPresenterImpl;

public class SplashActivity extends AppCompatActivity implements SplashView {


    public static boolean chkfalse = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ApplicationController api = new ApplicationController();

        final SplashPresenterImpl splashPresenterImpl = new SplashPresenterImpl(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashPresenterImpl.connectServer();
            }
        }, 2000);

        api.setCheck(chkfalse);


    }

    @Override
    public void connectingSucceed(int statusCode) {
        Intent intent;
        if(statusCode == 200){
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }
        else if(statusCode == 401){
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }
        else {
            return;
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void networkError() {
        ErrorController errorController = new ErrorController(getApplicationContext());
        errorController.notifyNetworkError();
    }

}
