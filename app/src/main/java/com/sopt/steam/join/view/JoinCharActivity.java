package com.sopt.steam.join.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sopt.steam.ErrorController;
import com.sopt.steam.R;
import com.sopt.steam.join.component.CharacterAdapter;
import com.sopt.steam.join.model.JoinUser;
import com.sopt.steam.join.presenter.JoinPresenter;
import com.sopt.steam.join.presenter.JoinPresenterImpl;
import com.sopt.steam.main.view.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinCharActivity extends AppCompatActivity implements JoinCharView {

    JoinPresenter presenter;
    Intent intent;

    ViewPager pager;
    Toast toast;
    Handler handler;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_char);

        ButterKnife.bind(this);

        presenter = new JoinPresenterImpl(this);

        pager = (ViewPager) findViewById(R.id.vp_character);

        //ViewPager에 설정할 Adapter 객체 생성
        //ListView에서 사용하는 Adapter와 같은 역할.
        //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
        //PagerAdapter를 상속받은 Characterdapter 객체 생성
        //Characterdapter LayoutInflater 객체 전달
        CharacterAdapter adapter = new CharacterAdapter(getLayoutInflater());

        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);

        intent = getIntent();


    }

    //onClick속성이 지정된 View를 클릭했을때 자동으로 호출되는 메소드
    public void mOnClick(View v) {



        switch (v.getId()) {
            case R.id.btn_previous://이전버튼 클릭
                position = pager.getCurrentItem();//현재 보여지는 아이템의 위치를 리턴
                if (position == 0) {
                    pager.setCurrentItem(2, true);
                    toast = Toast.makeText(this, "마지막 캐릭터 입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 600);
                } else {
                    if (position == 1) {
                        toast = Toast.makeText(this, "처음 캐릭터 입니다.", Toast.LENGTH_SHORT);
                        toast.show();
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 600);
                    }
                    pager.setCurrentItem(position - 1, true);
                }
                break;

            case R.id.btn_next://다음버튼 클릭
                position = pager.getCurrentItem();//현재 보여지는 아이템의 위치를 리턴

                if (position == 2) {
                    pager.setCurrentItem(0, true);
                    toast = Toast.makeText(this, "처음 캐릭터 입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 600);
                } else {
                    if (position == 1) {
                        toast = Toast.makeText(this, "마지막 캐릭터 입니다.", Toast.LENGTH_SHORT);
                        toast.show();
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 600);
                    }
                    pager.setCurrentItem(position + 1, true);
                }
                break;
        }

    }

    @Override
    public void networkError() {
        ErrorController errorController = new ErrorController(getApplicationContext());
        errorController.notifyNetworkError();
    }

    @Override
    public void registerSucceed() {
        Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.join_btn_join)
    public void setBtnJoin() {

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String pw = intent.getStringExtra("pw");
        String email = intent.getStringExtra("email");

        JoinUser user = new JoinUser(id, pw, name, email, position);

        presenter.registerToServer(user);


    }

}
