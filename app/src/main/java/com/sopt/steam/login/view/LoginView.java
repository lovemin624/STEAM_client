package com.sopt.steam.login.view;

public interface LoginView {
    void loginSucceed(String user_id);
    void loginFailed();
    void networkError();
}
