package com.signs.signsschool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs = 3;


    public ViewPagerAdapter( FragmentManager fm, int numberOfTabs) {
        super(fm);

        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                Tab1 t0 = new Tab1();

                return t0;
            case 1:

                Tab2 t1 = new Tab2();

                return t1;

            case 2:

                Tab3 t2 = new Tab3();

                return t2;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
