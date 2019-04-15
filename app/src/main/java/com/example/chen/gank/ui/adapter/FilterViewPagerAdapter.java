package com.example.chen.gank.ui.adapter;

import com.example.chen.gank.data.bean.GankFilterResult;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 22:22
 */
public class FilterViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public FilterViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
