package com.sopt.steam.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;
import com.sopt.steam.join.view.JoinActivity;
import com.sopt.steam.login.model.Authentication;
import com.sopt.steam.login.presenter.LoginPresenter;
import com.sopt.steam.login.presenter.LoginPresenterImpl;
import com.sopt.steam.main.view.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.login_edit_id)
    EditText login_edit_id;
    @Bind(R.id.login_edit_passwd)
    EditText login_edit_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        /*
        //로그인 버튼
        btn_login = (Button)findViewById(R.id.login_btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //회원가입 버튼
        btn_join = (Button)findViewById(R.id.login_btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        */

    }



    @OnClick(R.id.login_btn_join)
    public void setBtn_join() {
        Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_btn_login)
    public void setBtn_login() {
        sendData();
    }

    public void sendData() {

        String user_id = login_edit_id.getText().toString();
        String passwd = login_edit_passwd.getText().toString();

        Authentication authentication = null;

        authentication = new Authentication(user_id, passwd);
        LoginPresenter presenter = new LoginPresenterImpl(authentication, this);
        presenter.loginToServer();
    }

    @Override
    public void loginSucceed(String user_id) {
        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void networkError() {
        ErrorController errorController = new ErrorController(getApplicationContext());
        errorController.notifyNetworkError();
    }
}
