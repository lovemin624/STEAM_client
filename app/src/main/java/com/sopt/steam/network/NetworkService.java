package com.sopt.steam.network;

/**
 * Created by Min_Mac on 16. 4. 13..
 */

import com.sopt.steam.join.model.JoinUser;
import com.sopt.steam.login.model.Authentication;
import com.sopt.steam.login.model.User;
import com.sopt.steam.profile.model.UserProfile;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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

    //프로필 가져오기
    @GET("/profile/{user_id}")
    Call<UserProfile> getUserProfile(@Path("user_id") String user_id);

}
