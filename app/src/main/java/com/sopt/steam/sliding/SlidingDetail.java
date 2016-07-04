package com.sopt.steam.sliding;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.sopt.steam.R;
import com.sopt.steam.board.BoardView;
import com.sopt.steam.favorite.FavoriteSpace;


public class SlidingDetail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    CustomSwipeAdapter adapter1;                        //이미지 슬라이더를 위한 어댑터
    boolean check_star = true;                      //즐찾 체크
    double hart = 4.8;                                       //평점 매기는 친구 - 나중에 디비에서 가져와야 함 int로 지정해서 소수점 짤라쓰
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_room_detail);
        setContentView(R.layout.activity_room);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        // Get the ViewPager and set it's PagerAdapter so that it can display items

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        //이미지 슬라이더를 위한 어댑터
        viewPager =(ViewPager)findViewById(R.id.my_viewpager);
        adapter1 = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter1);


        final ImageView star_btn = (ImageView) findViewById(R.id.star);

        star_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (check_star == false) {
                    star_btn.setBackgroundResource(R.drawable.star);
                    check_star = true;
                } else {
                    star_btn.setBackgroundResource(R.drawable.defualt_star);
                    check_star = false;
                }

            }
        });

        //버튼 설정 - 지도,문의하기,후기 버튼
        Button map_btn = (Button) findViewById(R.id.mapButton);
        Button inq_btn = (Button) findViewById(R.id.inquiresButton);
        ImageButton review_btn = (ImageButton) findViewById(R.id.reviewButton);

        assert map_btn != null;
        assert inq_btn != null;
        assert review_btn != null;

        map_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SlidingDetail.class);  //수정 필요
                startActivity(intent);

                finish();
            }
        });


        inq_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Inquires dialog = Inquires.newInstance();

                dialog.show(getFragmentManager(), "title");
            }
        });

        review_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Review.class);
                startActivity(intent);

                //finish();
            }
        });

        //평점을 시각화해주는 하트~
        final ImageView hart01 = (ImageView) findViewById(R.id.hart01);
        final ImageView hart02 = (ImageView) findViewById(R.id.hart02);
        final ImageView hart03 = (ImageView) findViewById(R.id.hart03);
        final ImageView hart04 = (ImageView) findViewById(R.id.hart04);
        final ImageView hart05 = (ImageView) findViewById(R.id.hart05);

        if (hart < 1) {
            if (hart - (int)hart < 0.2)
                hart01.setBackgroundResource(R.drawable.defualt_hart);
            else if(hart - (int)hart < 0.7)
                hart01.setBackgroundResource(R.drawable.half_hart);
            else
                hart01.setBackgroundResource(R.drawable.hart);
        }

        else if (hart < 2) {
            hart01.setBackgroundResource(R.drawable.hart);

            if (hart - (int)hart < 0.2)
                hart02.setBackgroundResource(R.drawable.defualt_hart);
            else if(hart - (int)hart < 0.7)
                hart02.setBackgroundResource(R.drawable.half_hart);
            else
                hart02.setBackgroundResource(R.drawable.hart);
        }

        else if (hart < 3) {
            hart01.setBackgroundResource(R.drawable.hart);
            hart02.setBackgroundResource(R.drawable.hart);

            if (hart - (int)hart < 0.2)
                hart03.setBackgroundResource(R.drawable.defualt_hart);
            else if(hart - (int)hart < 0.7)
                hart03.setBackgroundResource(R.drawable.half_hart);
            else
                hart03.setBackgroundResource(R.drawable.hart);
        }

        else if (hart < 4) {
            hart01.setBackgroundResource(R.drawable.hart);
            hart02.setBackgroundResource(R.drawable.hart);
            hart03.setBackgroundResource(R.drawable.hart);

            if (hart - (int)hart < 0.2)
                hart04.setBackgroundResource(R.drawable.defualt_hart);
            else if(hart - (int)hart < 0.7)
                hart04.setBackgroundResource(R.drawable.half_hart);
            else
                hart04.setBackgroundResource(R.drawable.hart);

            hart05.setBackgroundResource(R.drawable.defualt_hart);
        }


        else if (hart < 5) {
            hart01.setBackgroundResource(R.drawable.hart);
            hart02.setBackgroundResource(R.drawable.hart);
            hart03.setBackgroundResource(R.drawable.hart);
            hart04.setBackgroundResource(R.drawable.hart);

            if (hart - (int)hart < 0.2)
                hart05.setBackgroundResource(R.drawable.defualt_hart);
            else if(hart - (int)hart < 0.7)
                hart05.setBackgroundResource(R.drawable.half_hart);
            else
                hart05.setBackgroundResource(R.drawable.hart);
        }

        else if (hart == 5){
            hart01.setBackgroundResource(R.drawable.hart);
            hart02.setBackgroundResource(R.drawable.hart);
            hart03.setBackgroundResource(R.drawable.hart);
            hart04.setBackgroundResource(R.drawable.hart);
            hart05.setBackgroundResource(R.drawable.hart);
        }


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
