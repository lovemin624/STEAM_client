package com.sopt.steam.main.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sopt.steam.ErrorController;
import com.sopt.steam.MyAdapter;
import com.sopt.steam.MyData;
import com.sopt.steam.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    private SearchView searchView;
    private MenuItem searchItem;

    private Button moveTop;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        /**
         * kh 2016 6 28
         */


//        searchBtn = (Button)findViewById(R.id.SearchBtn);
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),kakaoTalk.class);
//                startActivity(intent);
//            }
//        });



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


        myDataset.add(new MyData("#창조공간","경기도 수원시 영통구","1500원 / 1시간","평점 4.5" , R.drawable.img1));
        myDataset.add(new MyData("#괴테하우스","경기도 수원시 영통구","2500원 / 1시간","평점 4.5" , R.drawable.img2));
        myDataset.add(new MyData("#카페명","경기도 수원시 영통구","3500원 / 1시간","평점 4.5" , R.drawable.img3));



        moveTop = (Button)findViewById(R.id.moveTop);
        moveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //맨아래로
                //mRecyclerView.smoothScrollToPosition(v.getTop());

                mRecyclerView.smoothScrollToPosition(0);
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy < 0) {
                    // Recycle view scrolling up...

                } else if (dy > 0) {
                    // Recycle view scrolling down...

//                    int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
//                    int totalItemCount = mLayoutManager.getItemCount();

                    int scrollOffset = mRecyclerView.computeVerticalScrollOffset();
                    int scrollExtend = mRecyclerView.computeVerticalScrollExtent();
                    int scrollRange = mRecyclerView.computeVerticalScrollRange();

//                    Log.i("test","scrollOffset "+String.valueOf(scrollOffset));
//                    Log.i("test","scrollExtend "+String.valueOf(scrollExtend));
//                    Log.i("test","scrollRange "+String.valueOf(scrollRange));

                    if( scrollOffset + scrollExtend == scrollRange || scrollOffset + scrollExtend -1 == scrollRange ){


//                    if (lastVisibleItem >= totalItemCount - 10) {



                        myDataset.add(new MyData("#추가1","경기도 수원시 영통구","1500원 / 1시간","평점 4.5" ,R.drawable.img1));
                        myDataset.add(new MyData("#추가2","경기도 수원시 영통구","2500원 / 1시간","평점 4.5" , R.drawable.img2));
                        myDataset.add(new MyData("#추가3","경기도 수원시 영통구","3500원 / 1시간","평점 4.5" , R.drawable.img3));

                        mAdapter.notifyDataSetChanged() ;

//                        Toast.makeText(getApplicationContext(),"맨아래",Toast.LENGTH_SHORT).show();
                    }


                }
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
        getMenuInflater().inflate(R.menu.main, menu);


        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
//        searchView.setQueryHint(getString(R.string.edittext_search_video));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * 검색어 입력시 : onQueryTextChange
             * 검색어 완료시 : onQueryTextSubmit
             * @param s
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                searchView.clearFocus();

//                String suggestWord = intent.getDataString();
//                searchView.setQuery(suggestWord, false);
//
//                searchItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }

        });

        return true;
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
            // Handle the camera action
        } else if (id == R.id.nav_myPlace) {

        } else if (id == R.id.nav_myLike) {

        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    @OnClick (R.id.toolbar_login)
    public void setToolbar_login() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
*/

    public void sendSucceed() {
        Toast.makeText(this, "전송완료!", Toast.LENGTH_LONG).show();

    }


    public void sendFailure() {
        Toast.makeText(this, "전송실패!", Toast.LENGTH_LONG).show();
    }


    public void networkError() {
        ErrorController errorController = new ErrorController(this.getApplicationContext());
        errorController.notifyNetworkError();
    }


}
