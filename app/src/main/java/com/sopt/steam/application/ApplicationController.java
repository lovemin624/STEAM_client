package com.sopt.steam.application;

import android.app.Application;

import com.sopt.steam.login.model.User;
import com.sopt.steam.network.NetworkService;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApplicationController extends Application {

    private static ApplicationController instance;
    public static ApplicationController getInstance(){
        return instance;
    }

    private NetworkService networkService;
    public NetworkService getNetworkService() {
        return networkService;
    }

    private static String baseUrl = "http://52.78.8.18:5000";

    public String getBaseUrl() {
        return baseUrl;
    }

    private static Retrofit.Builder builder = new Retrofit.Builder();

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        this.buildService();
    }

    private void buildService() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient client = new OkHttpClient();
        client.setCookieHandler(cookieManager);
        client.interceptors().clear();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                //Log.i("MyTag", "요청 주소 : " + original.httpUrl());

                Request.Builder requestBuilder = original.newBuilder()
                        .header("State", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        networkService =  retrofit.create(NetworkService.class);
}

    private static User loginUser;
    public void setLoginUser(User user){
        loginUser = user;
    }

    public User getLoginUser(){
        return loginUser;
    }


    public static boolean chkBoolean = false;
    public void setCheck(boolean chkBoolean) {
        this.chkBoolean = chkBoolean;
    }

    public boolean getCheck() {
        return chkBoolean;
    }

}
