package com.sopt.steam.join.presenter;

import android.util.Log;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.join.model.JoinUser;
import com.sopt.steam.join.view.JoinView;
import com.sopt.steam.login.model.User;
import com.sopt.steam.network.NetworkService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Min_Mac on 16. 4. 14..
 */
public class JoinPresenterImpl implements JoinPresenter {

    NetworkService networkService;
    JoinView view;

    public JoinPresenterImpl(JoinView view) {
        this.view = view;
        networkService = ApplicationController.getInstance().getNetworkService();
    }

    @Override
    public void registerToServer(JoinUser user) {
        Call<User> registerCall = networkService.registerUser(user);
        registerCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    view.registerSucceed();
                }
                else {
                    Log.i("MyTag", ""+ response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.networkError();
            }
        });
    }
}
