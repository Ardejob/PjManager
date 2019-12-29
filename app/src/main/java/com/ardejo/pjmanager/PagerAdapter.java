package com.ardejo.pjmanager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int mNumberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return tab1Fragment;
            case 1:
                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return  tab2Fragment;
            case 2:
                Tab3Fragment tab3Fragment1 = new Tab3Fragment();
                return tab3Fragment1;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
