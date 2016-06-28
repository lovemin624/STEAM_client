package com.sopt.steam.login.presenter;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.login.model.Authentication;
import com.sopt.steam.login.model.User;
import com.sopt.steam.login.view.LoginView;
import com.sopt.steam.network.NetworkService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginPresenterImpl implements LoginPresenter{

    ApplicationController api;
    Authentication authentication;
    LoginView view;
    NetworkService networkService;

    public LoginPresenterImpl(Authentication authentication, LoginView view) {
        this.authentication = authentication;
        this.view = view;
        api = ApplicationController.getInstance();
        networkService = api.getNetworkService();
    }

    @Override
    public void loginToServer() {
        Call<User> login = networkService.login(authentication);

        login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    User user = response.body();
                    api.setLoginUser(user);

                    view.loginSucceed(authentication.user_id);

                }
                else{
                    view.loginFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.networkError();
            }
        });
    }



}
