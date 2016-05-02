/**
 * Acknowledgement: http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
**/

package com.play.eldarbabayev2.easylearning.views.classes;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.Utils;

import java.util.ArrayList;
import java.util.List;

public class MessengerSwipe extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView titleTextView = (TextView) toolbar.findViewById(R.id.class_name);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        String key = getIntent().getExtras().getString("groupKey");

        String groupName = getIntent().getExtras().getString("groupName");
        Log.d("AAAAA", groupName);

        titleTextView.setText(groupName);
        Utils.setTypefaceMedium(titleTextView, this);

        setupViewPager(viewPager, key);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Utils.changeTabsFont(tabLayout, this);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    private void setupViewPager(ViewPager viewPager, String groupKey) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle args = new Bundle();
        args.putString("groupKey", groupKey);
        TeacherChatFragment tf = new TeacherChatFragment();
        GroupChatFragment gf = new GroupChatFragment();
        tf.setArguments(args);
        gf.setArguments(args);
        adapter.addFragment(tf, "LEARN");
        adapter.addFragment(gf, "CHAT");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}