package com.sopt.steam.profile.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;
import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.profile.model.UserProfile;
import com.sopt.steam.profile.presenter.ProfilePresenter;
import com.sopt.steam.profile.presenter.ProfilePresenterImpl;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    TextView profile_id;
    TextView profile_passwd;
    TextView profile_name;


    ApplicationController api = ApplicationController.getInstance();
    ProfilePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        api = ApplicationController.getInstance();
        presenter = new ProfilePresenterImpl(this);
        presenter.getDataFromServer();

        profile_id = (TextView)findViewById(R.id.profile_id);
        profile_passwd = (TextView)findViewById(R.id.profile_passwd);
        profile_name = (TextView)findViewById(R.id.profile_name);


    }


    @Override
    protected void onResume() {
        super.onResume();

        ProfilePresenter presenter = new ProfilePresenterImpl(this);
        presenter.getDataFromServer();
    }

    @Override
    public void setProfileTexts(UserProfile userProfile) {

        profile_id.setText(userProfile.user_id);
        profile_passwd.setText(userProfile.passwd);
        profile_name.setText(userProfile.name);
    }

    @Override
    public void networkError() {
        ErrorController errorController = new ErrorController(getApplicationContext());
        errorController.notifyNetworkError();
    }
}