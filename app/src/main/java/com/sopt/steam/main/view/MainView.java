package com.sopt.steam.main.view;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

/**
 * Created by Min_Mac on 16. 5. 3..
 */
public interface MainView extends NavigationView.OnNavigationItemSelectedListener {
    void sendSucceed();
    void sendFailure();
    void networkError();

//    void connectingSucceed(int statusCode);

    public boolean onNavigationItemSelected(MenuItem item);
}
