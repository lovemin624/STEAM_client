package com.sopt.steam.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sopt.steam.R;
import com.sopt.steam.board.BoardView;
import com.sopt.steam.database.DbOpenHelper;
import com.sopt.steam.database.ItemData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by 즤 on 2016-07-02.
 */
public class FavoriteSpace extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private CustomFavoriteAdater adapter;
    private DbOpenHelper mDbOpenHelper;

    private Button deleteBookmark;
    private Button sendKakao;


    private ArrayList<ItemData> itemDatas = null;
    private List<FavoriteSpaceData> item = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        itemDatas = mDbOpenHelper.DbSelect();

        if(itemDatas.size() == 0){
            Toast.makeText(getApplicationContext(),"즐겨찾기 항목이 없습니다.",Toast.LENGTH_SHORT).show();
        }
        else{

            item = new ArrayList<FavoriteSpaceData>();
            listView = (ListView) findViewById(R.id.listView);

            for(int i = 0 ; i < itemDatas.size() ; i++)
            {
                String bookmarkId = String.valueOf(itemDatas.get(i).Id);
                String userId = itemDatas.get(i).userId;
                String roomId = itemDatas.get(i).roomId;
                String imgurl = itemDatas.get(i).image;
                String roomName = itemDatas.get(i).roomName;
                String info1 = itemDatas.get(i).content;
                String info2 = itemDatas.get(i).tag;

                item.add(new FavoriteSpaceData(bookmarkId,userId,roomId,imgurl,roomName,info1,info2));
            }

            adapter = new CustomFavoriteAdater(getApplicationContext(), item);
            listView.setAdapter(adapter);
        }


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
