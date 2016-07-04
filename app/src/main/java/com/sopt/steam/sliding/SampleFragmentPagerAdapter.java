package com.sopt.steam.sliding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sopt.steam.sliding.PriceTime.PriceTime;
import com.sopt.steam.sliding.information.information;
import com.sopt.steam.sliding.notice.notice;
import com.sopt.steam.sliding.reviewList.ReviewList;


/**
 * Created by user on 2016-07-02.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3", "Tab4"};

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return information.newInstance(position + 1);
        else if(position==1)
            return PriceTime.newInstance(position + 1);
        else if(position==2)
            return notice.newInstance(position + 1);
        else
            return ReviewList.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
