package com.kidskart.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kidskart.fragment.ProductImageFragment;

/**
 * Created by Nilesh on 20/09/15.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        return new ProductImageFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
