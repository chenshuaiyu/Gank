package com.example.chen.gank.ui.filter;

import android.os.Bundle;
import android.view.View;

import com.example.chen.gank.R;
import com.example.chen.gank.ui.adapter.FilterViewPagerAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 20:19
 */
public class FilterFragment extends BaseFragment {
    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private FilterViewPagerAdapter mFilterViewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_filter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);

        mFragments = new ArrayList<>();
        mFilterViewPagerAdapter = new FilterViewPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setAdapter(mFilterViewPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
    }
}
