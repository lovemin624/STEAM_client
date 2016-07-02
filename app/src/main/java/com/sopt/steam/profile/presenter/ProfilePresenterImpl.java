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
    String user_id;

    public ProfilePresenterImpl(ProfileView view) {
        this.view = view;
        api = ApplicationController.getInstance();
        user_id = api.getLoginUser().user_id;
    }

    @Override
    public void getDataFromServer() {
        NetworkService networkService = api.getNetworkService();
        Call<UserProfile> userProfileCall = networkService.getUserProfile(user_id);
        Log.i("MyTag", "진행 확인 : " + "getDataFromServer2");
        userProfileCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Response<UserProfile> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    Log.i("MyTag", "진행 확인 : " + "getDataFromServer3");
                    view.setProfileTexts(response.body());
                    Log.i("MyTag", "UserProfile :" + response.body());



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
}
