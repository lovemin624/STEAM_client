package com.sopt.steam.profile.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.sopt.steam.R;
import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.profile.model.UserProfile;
import com.sopt.steam.profile.presenter.ProfilePresenter;
import com.sopt.steam.profile.presenter.ProfilePresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.profile_id)
    TextView profile_id;
    @Bind(R.id.profile_passwd)
    TextView profile_passwd;
    @Bind(R.id.profile_name)
    TextView profile_name;

    ApplicationController api = ApplicationController.getInstance();
    String id, img_profile, img_cover;
    int value;

    private static String signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        initToolbar();
        id = api.getLoginUser().user_id;

    }

    private void initToolbar() {

        if (toolbar != null) {
            toolbar.setTitle("프로필");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ProfilePresenter presenter = new ProfilePresenterImpl(this);
        signature = intent.getStringExtra("signature");
        presenter.setProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
            intent.putExtra("from", "profile");
            intent.putExtra("signature", signature);
            startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    protected void onResume() {
        super.onResume();

        ProfilePresenter presenter = new ProfilePresenterImpl(this);
        presenter.setProfile();
    }

    @Override
    public void setProfileTexts(UserProfile userProfile) {

        profile_id.setText(userProfile.user_id);
        profile_passwd.setText(userProfile.passwd);
        profile_name.setText(userProfile.name);
    }
}
