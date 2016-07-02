package com.sopt.steam.profile.view;

import com.sopt.steam.profile.model.UserProfile;

public interface ProfileView {
    void setProfileTexts(UserProfile userProfile);
    void networkError();
}
