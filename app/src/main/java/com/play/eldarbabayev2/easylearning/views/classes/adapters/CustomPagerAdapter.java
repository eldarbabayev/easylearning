package com.play.eldarbabayev2.easylearning.views.classes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.views.classes.AccountPageFragment;
import com.play.eldarbabayev2.easylearning.views.classes.GroupChatFragment;
import com.play.eldarbabayev2.easylearning.views.classes.TeacherChatFragment;

public class CustomPagerAdapter extends
        FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    private static int[] ICONS = new int[] {
            R.drawable.first_tab_drawable,
            R.drawable.second_tab_drawable,
            R.drawable.third_tab_drawable
    };


    public CustomPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new TeacherChatFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new GroupChatFragment();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new AccountPageFragment();
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount()
    {
        return ICONS.length;
    }

    @Override
    public int getPageIconResId(int position) {
        return ICONS[position];
    }
}

