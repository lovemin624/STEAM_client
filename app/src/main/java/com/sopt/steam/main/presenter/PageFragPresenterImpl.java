package com.sopt.steam.main.presenter;

import android.util.Log;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.main.model.RecModel;
import com.sopt.steam.main.view.MainView;
import com.sopt.steam.network.NetworkService;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Min_Mac on 16. 5. 3..
 */
public class PageFragPresenterImpl implements PageFragPresenter {

    ApplicationController api;
    MainView view;

    public PageFragPresenterImpl(MainView view) {
        this.view = view;
        api = ApplicationController.getInstance();
    }

    @Override
    public void postToServer(final RecModel recModel, RequestBody recData) {
        NetworkService networkService = api.getNetworkService();
        Call<RecModel> recModelCall = networkService.postToServer(recData, recModel);

        recModelCall.enqueue(new Callback<RecModel>() {
            @Override
            public void onResponse(Response<RecModel> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    api.setRecModel(recModel);

                    view.sendSucceed();
                    Log.i("MyTag", "상태코드_succeed : " + response.code());
                }
                else{
                    view.sendFailure();
                    Log.i("MyTag", "상태코드 : " + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.networkError();
            }
        });
    }
}
