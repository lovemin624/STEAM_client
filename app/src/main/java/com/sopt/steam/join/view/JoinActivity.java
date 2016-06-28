package com.sopt.steam.join.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;
import com.sopt.steam.join.model.JoinUser;
import com.sopt.steam.join.presenter.JoinPresenter;
import com.sopt.steam.join.presenter.JoinPresenterImpl;
import com.sopt.steam.login.view.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Min_Mac on 16. 4. 14..
 */
public class JoinActivity extends AppCompatActivity implements JoinView {

    JoinPresenter presenter;

    @Bind(R.id.join_edit_id)
    EditText join_edit_id;
    @Bind(R.id.join_edit_name)
    EditText join_edit_name;
    @Bind(R.id.join_edit_passwd)
    EditText join_edit_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        presenter = new JoinPresenterImpl(this);
        ButterKnife.bind(this);

    /*
        //가입하기 버튼
        btn_join = (Button)findViewById(R.id.join_btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //취소 버튼
        btn_cancel = (Button)findViewById(R.id.join_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    */
    }

    @OnClick(R.id.join_btn_join)
    public void setBtn_join() {

        sendData();
    }

    private void sendData() {

            String id = join_edit_id.getText().toString();
            String passwd = join_edit_passwd.getText().toString();
            String name = join_edit_name.getText().toString();

            JoinUser user = new JoinUser(id, passwd, name);

            presenter.registerToServer(user);

    }

    @OnClick(R.id.join_btn_cancel)
    public void setBtn_cancel() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void networkError() {
        ErrorController errorController = new ErrorController(getApplicationContext());
        errorController.notifyNetworkError();
    }

    @Override
    public void registerSucceed() {
        Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
