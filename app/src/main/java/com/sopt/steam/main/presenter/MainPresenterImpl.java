package com.sopt.steam.main.presenter;

import com.sopt.steam.application.ApplicationController;
import com.sopt.steam.main.model.MainStatus;
import com.sopt.steam.main.view.MainView;

/**
 * Created by Min_Mac on 16. 5. 3..
 */
public class MainPresenterImpl implements MainPresenter {

    ApplicationController api;
    MainView view;
    MainStatus status;


/*
    public MainPresenterImpl(MainView view) {
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
*/

    /*
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
    */
}
