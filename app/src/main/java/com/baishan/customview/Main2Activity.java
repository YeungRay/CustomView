package com.baishan.customview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    private String[] mTitles = new String[]{"简介", "评价", "相关","哈哈"};
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private List<ColorTrackView> mTabs = new ArrayList<ColorTrackView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initViews();
        initDatas();
        initEvents();

    }

    private void initEvents() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

                if (positionOffset > 0) {
                    System.out.println(position);
                    ColorTrackView left = mTabs.get(position);
                    ColorTrackView right = mTabs.get(position + 1);

                    left.setDirection(1);
                    right.setDirection(0);
                    Log.e("TAG", positionOffset + "");
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDatas() {

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabs.add((ColorTrackView) findViewById(R.id.id_tab_01));
        mTabs.add((ColorTrackView) findViewById(R.id.id_tab_02));
        mTabs.add((ColorTrackView) findViewById(R.id.id_tab_03));
        mTabs.add((ColorTrackView) findViewById(R.id.id_tab_04));
    }


}
