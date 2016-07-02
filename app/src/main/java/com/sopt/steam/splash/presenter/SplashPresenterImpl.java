package com.sopt.steam.splash.presenter;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.login.model.User;
import com.sopt.steam.network.NetworkService;
import com.sopt.steam.splash.view.SplashView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashPresenterImpl implements SplashPresenter {

    ApplicationController api;
    SplashView view;

    public SplashPresenterImpl(SplashView view) {
        this.view = view;
        api = ApplicationController.getInstance();
    }

    @Override
    public void connectServer() {
        NetworkService networkService = api.getNetworkService();
        Call<User> loginTest = networkService.getSession();
        loginTest.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                view.connectingSucceed(response.code());
            }

            @Override
            public void onFailure(Throwable t) {
                view.networkError();
            }
        });
    }

}
