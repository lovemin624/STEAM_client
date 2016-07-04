package com.sopt.steam.board;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sopt.steam.R;
import com.sopt.steam.favorite.FavoriteSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class BoardView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listview;
    private Button moveTop;
    private Button writeBoard;
    private EditText searchArea;

    private BoardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        moveTop = (Button)findViewById(R.id.boardTop);
        writeBoard = (Button)findViewById(R.id.boardWrite);
        searchArea = (EditText)findViewById(R.id.edit_Search);
        listview = (ListView)findViewById(R.id.ListView1);

        moveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listview.smoothScrollToPosition(0);
            }
        });

        writeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BoardRegister.class);
                startActivity(intent);
            }
        });


        List<BoardItem> item = new ArrayList<BoardItem>();

        item.add(new BoardItem("안드로이드 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("자바 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("C언어 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("웹 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("디자인 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("기획 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("꿀잠 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("놀기 스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));
        item.add(new BoardItem("스터디 멤버 구합니다~","댓글 수","닉네임","작성날짜","스터디 유형","지역"));


        adapter = new BoardAdapter(getApplicationContext(),item);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(),BoardDetail.class);
                startActivity(intent);
            }
        });

        // Capture Text in EditText
        searchArea.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = searchArea.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myPage) {
            // Handle the camera action
        } else if (id == R.id.nav_myPlace) {
            Log.i("test","gg");
            Intent intent = new Intent(getApplicationContext(), FavoriteSpace.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_myLike) {

        } else if (id == R.id.nav_monStory) {

            Intent intent = new Intent(getApplicationContext(), BoardView.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
