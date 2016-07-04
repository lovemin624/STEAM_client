package com.sopt.steam.sliding;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sopt.steam.R;
import com.sopt.steam.board.BoardView;
import com.sopt.steam.favorite.FavoriteSpace;


/**
 * Created by 즤 on 2016-06-29.
 */
public class Review extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    String[] Data = null;
    ArrayAdapter<String> adapter = null;
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_review);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final ImageView hart01 = (ImageView) findViewById(R.id.first_hart);
        final ImageView hart02 = (ImageView) findViewById(R.id.second_hart);
        final ImageView hart03 = (ImageView) findViewById(R.id.third_hart);
        final ImageView hart04 = (ImageView) findViewById(R.id.fourth_hart);
        final ImageView hart05 = (ImageView) findViewById(R.id.fifth_hart);

        hart01.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hart01.setBackgroundResource(R.drawable.hart);
                hart02.setBackgroundResource(R.drawable.defualt_hart);
                hart03.setBackgroundResource(R.drawable.defualt_hart);
                hart04.setBackgroundResource(R.drawable.defualt_hart);
                hart05.setBackgroundResource(R.drawable.defualt_hart);
            }
        });

        hart02.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hart01.setBackgroundResource(R.drawable.hart);
                hart02.setBackgroundResource(R.drawable.hart);
                hart03.setBackgroundResource(R.drawable.defualt_hart);
                hart04.setBackgroundResource(R.drawable.defualt_hart);
                hart05.setBackgroundResource(R.drawable.defualt_hart);
            }
        });

        hart03.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hart01.setBackgroundResource(R.drawable.hart);
                hart02.setBackgroundResource(R.drawable.hart);
                hart03.setBackgroundResource(R.drawable.hart);
                hart04.setBackgroundResource(R.drawable.defualt_hart);
                hart05.setBackgroundResource(R.drawable.defualt_hart);
            }
        });

        hart04.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hart01.setBackgroundResource(R.drawable.hart);
                hart02.setBackgroundResource(R.drawable.hart);
                hart03.setBackgroundResource(R.drawable.hart);
                hart04.setBackgroundResource(R.drawable.hart);
                hart05.setBackgroundResource(R.drawable.defualt_hart);
            }
        });

        hart05.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hart01.setBackgroundResource(R.drawable.hart);
                hart02.setBackgroundResource(R.drawable.hart);
                hart03.setBackgroundResource(R.drawable.hart);
                hart04.setBackgroundResource(R.drawable.hart);
                hart05.setBackgroundResource(R.drawable.hart);
            }
        });


        Button review_btn = (Button) findViewById(R.id.reviewReportButton);

        assert review_btn != null;

       review_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SlidingDetail.class);  //수정 필요
                startActivity(intent);

                finish();
            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);

        String [] Data = getResources().getStringArray(R.array.studyRoomName);      //Spinner에 필요한 데이터 준비
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Data);    //어뎁터에 생성 및 준비된 데이터를 설정
        spinner.setAdapter(adapter);                                //스피너에 어뎁터를 설정
        //simple_dropdown_item_1line: 안드로이드에서 편의를 위해 제공하는 스피너 전용 레이아웃 리소르
        //기본 리소스는 android.R.layout으로 시작함ut으로 시작함
    }

    public void reviewBack (View view)
    {
        finish();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            Toast.makeText(getApplicationContext(),"Searching",Toast.LENGTH_SHORT).show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_myPage) {


        } else if (id == R.id.nav_myPlace) {
            Intent intent = new Intent(getApplicationContext(), FavoriteSpace.class);
            startActivity(intent);

        } else if (id == R.id.nav_myLike) {

            Intent intent = new Intent(getApplicationContext(), SlidingDetail.class);
            startActivity(intent);


        } else if (id == R.id.nav_monStory) {
            Intent intent = new Intent(getApplicationContext(), BoardView.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
