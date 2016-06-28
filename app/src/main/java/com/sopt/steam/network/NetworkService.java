package com.sopt.steam.network;

/**
 * Created by Min_Mac on 16. 4. 13..
 */

import com.sopt.steam.join.model.JoinUser;
import com.sopt.steam.login.model.Authentication;
import com.sopt.steam.login.model.User;
import com.sopt.steam.main.model.RecModel;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

public interface NetworkService {

    @GET("/session")
    Call<User> getSession();

    //로그인
    @POST("/session/sign/in")
    Call<User> login(@Body Authentication authentication);

    //로그아웃
    @GET("/session/sign/out")
    Call<Object> logout();

    //회원가입
    @POST("/membership")
    Call<User> registerUser(@Body JoinUser user);

    //녹음
    @Multipart
    @POST("/rec")
    Call<RecModel> postToServer(@Part("recdata\"; filename=\"rec.mp4\" ") RequestBody recData,
                                @Part("contents") RecModel recModel);

}
