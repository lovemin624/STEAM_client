package com.sopt.steam.profile.presenter;

import android.util.Log;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.network.NetworkService;
import com.sopt.steam.profile.model.UserProfile;
import com.sopt.steam.profile.view.ProfileView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class ProfilePresenterImpl implements ProfilePresenter{

    ApplicationController api;
    ProfileView view;

    public ProfilePresenterImpl(ProfileView view) {
        this.view = view;
        api = ApplicationController.getInstance();
    }

    @Override
    public void getDataFromServer(String user_id) {
        NetworkService networkService = api.getNetworkService();
        Call<UserProfile> userProfileCall = networkService.getUserProfile(user_id);
        userProfileCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Response<UserProfile> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    UserProfile userProfile = response.body();
                    api.setUserProfile(userProfile);
                    setProfile();

                } else {
                    Log.i("MyTag", "상태 코드 : " + response.code());
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.i("MyTag", "에러 내용 : " + t.getMessage());
            }
        });
    }

    @Override
    public void setProfile() {
        view.setProfileTexts(api.getUserProfile());
    }
}
