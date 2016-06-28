package com.sopt.steam.splash.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;
import com.sopt.steam.login.view.LoginActivity;
import com.sopt.steam.main.view.MainActivity;
import com.sopt.steam.splash.presenter.SplashPresenterImpl;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashPresenterImpl splashPresenterImpl = new SplashPresenterImpl(this);
        splashPresenterImpl.connectServer();
    }

    @Override
    public void connectingSucceed(int statusCode) {
        Intent intent;
        if(statusCode == 200){
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }
        else if(statusCode == 401){
            intent = new Intent(getApplicationContext(), LoginActivity.class);
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
