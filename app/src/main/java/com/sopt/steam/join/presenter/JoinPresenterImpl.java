package com.sopt.steam.join.presenter;

import android.util.Log;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.join.model.JoinUser;
import com.sopt.steam.join.view.JoinCharView;
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
    JoinCharView viewC;

    public JoinPresenterImpl(JoinView view) {
        this.view = view;
        networkService = ApplicationController.getInstance().getNetworkService();
    }

    public JoinPresenterImpl(JoinCharView view) {
        this.viewC = view;
        networkService = ApplicationController.getInstance().getNetworkService();
    }

    @Override
    public void registerToServer(JoinUser user) {
        Call<User> registerCall = networkService.registerUser(user);
        registerCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    viewC.registerSucceed();
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

    @Override
    public void checkIdDuplication(String user_id) {
        Call<String> duplicationCall = networkService.duplicationTest(user_id);
        duplicationCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    view.isDuplicated(response.body());
                } else {
                    Log.i("MyTag", "중복 확인 테스트 실패 코드 : " + response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void checkNameDuplication(String name) {
        Log.i("MyTag", "confirm1");
        Call<String> duplicationCall = networkService.duplicationName(name);
        Log.i("MyTag", "confirm2");
        duplicationCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Log.i("MyTag", "confirm3");
                if (response.isSuccess()) {
                    Log.i("MyTag", "confirm4");
                    view.isNameDuplicated(response.body());
                    Log.i("MyTag", "confirm5" + response.body());
                } else {
                    Log.i("MyTag", "중복 확인 테스트 실패 코드 : " + response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
