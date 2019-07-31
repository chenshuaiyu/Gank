package com.example.chen.gank.ui.filter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.chen.gank.R2;
import com.example.chen.gank.app.Constants;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.ui.adapter.FilterViewPagerAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 20:19
 */
public class FilterFragment extends BaseFragment {
    @BindView(R2.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    private List<FilterDetailFragment> mFragments;
    private FilterViewPagerAdapter mFilterViewPagerAdapter;
    private FilterViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_filter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(FilterViewModel.class);

        mFragments = new ArrayList<>();
        for (String type : Constants.FILTER_TYPE) {
            mFragments.add(new FilterDetailFragment(type));
        }
        mFilterViewPagerAdapter = new FilterViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mFilterViewPagerAdapter);
//        mTabLayout.setViewPager(mViewPager);//androidx问题
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_filter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.commit:

                break;
            default:
                break;
        }
        return true;
    }
}
