package com.sopt.steam.profile.view;

import android.support.design.widget.NavigationView;

import com.sopt.steam.profile.model.UserProfile;

public interface ProfileView extends NavigationView.OnNavigationItemSelectedListener{
    void setProfileTexts(UserProfile userProfile);
    void networkError();
}
