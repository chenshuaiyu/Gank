package com.example.chen.gank.ui.latest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.chen.gank.Constants;
import com.example.chen.gank.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.ui.MeiZhiActivity;
import com.example.chen.gank.ui.adapter.LatestAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.example.chen.gank.utils.GlideImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 20:57
 */
public class LatestFragment extends BaseFragment {
    private Banner mBanner;
    private TabLayout mTabLayout;

    private RecyclerView mRecyclerView;
    private List<Gank> mGanks;
    private LatestAdapter mLatestAdapter;
    private LatestViewModel mViewModel;
    private List<String> mDayList;
    private HashMap<String, GankDailyResult> mResults;

    private boolean isDayHistoryShow = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBanner = view.findViewById(R.id.banner);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mGanks = new ArrayList<>();
        mDayList = new ArrayList<>();
        mResults = new HashMap<>();

        setHasOptionsMenu(true);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(LatestViewModel.class);

        mLatestAdapter = new LatestAdapter(getActivity(), mGanks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mLatestAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        mViewModel.getGankDailyResults()
                .observe(this, gankDailyResult -> {
                            notifyDataSetChanged(gankDailyResult);
                            Log.d("CCC", "111");
                        }
                );

        mViewModel.getDayHistory()
                .observe(this, day -> {
                    for (int i = 0; i < 10; i++) {
                        String date = day.getResults().get(i);
                        mDayList.add(date);
                        mResults.put(date, null);

                        String[] split = date.split("-");
                        String s = split[1] + "." + split[2] + "\n" + split[0];
                        mTabLayout.addTab(mTabLayout.newTab().setText(s));
                    }
                    mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_date_white));
                    mTabLayout.setVisibility(View.GONE);
                });

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() < 10){
                    String date = mDayList.get(tab.getPosition());
                    if (mResults.get(date) != null){
                        GankDailyResult result = mResults.get(date);

                        notifyDataSetChanged(result);
                    } else {
                        String[] split = date.split("-");
                        mViewModel.getDay(split[0], split[1], split[2])
                                .observe(LatestFragment.this, gankDailyResult -> {
                                    mResults.put(date, gankDailyResult);

                                    notifyDataSetChanged(gankDailyResult);
                                });
                    }
                } else {
                    jumpToMoreDay();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() >= 10){
                    jumpToMoreDay();
                }
            }
        });
    }

    private void setData(GankDailyResult.GankDailyData results) {
        //Android
        if (results.getAndroid() != null) {
            Gank android = new Gank();
            android.set_id("-1");
            android.setDesc(Constants.FILTER_TYPE[0]);
            mGanks.add(android);
            mGanks.addAll(results.getAndroid());
        }
        //iOS
        if (results.getiOS() != null) {
            Gank ios = new Gank();
            ios.set_id("-1");
            ios.setDesc(Constants.FILTER_TYPE[1]);
            mGanks.add(ios);
            mGanks.addAll(results.getiOS());
        }
        //App
        if (results.getApp() != null) {
            Gank app = new Gank();
            app.set_id("-1");
            app.setDesc(Constants.FILTER_TYPE[2]);
            mGanks.add(app);
            mGanks.addAll(results.getApp());
        }
        //前端
        if (results.getFrontEnd() != null) {
            Gank frontEnd = new Gank();
            frontEnd.set_id("-1");
            frontEnd.setDesc(Constants.FILTER_TYPE[3]);
            mGanks.add(frontEnd);
            mGanks.addAll(results.getFrontEnd());
        }
        //瞎推荐
        if (results.getRecommend() != null) {
            Gank recommend = new Gank();
            recommend.set_id("-1");
            recommend.setDesc(Constants.FILTER_TYPE[4]);
            mGanks.add(recommend);
            mGanks.addAll(results.getRecommend());
        }
        //拓展资源
        if (results.getExpand() != null) {
            Gank expand = new Gank();
            expand.set_id("-1");
            expand.setDesc(Constants.FILTER_TYPE[5]);
            mGanks.add(expand);
            mGanks.addAll(results.getExpand());
        }
        //休息视频
        if (results.getVideo() != null) {
            Gank video = new Gank();
            video.set_id("-1");
            video.setDesc(Constants.FILTER_TYPE[6]);
            mGanks.add(video);
            mGanks.addAll(results.getVideo());
        }
    }

    private void setBanner(List<Gank> welfare) {
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(welfare);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setDelayTime(2500);
        mBanner.setOnBannerListener(position -> {
            Gank gank = welfare.get(position);
            startActivity(MeiZhiActivity.newIntent(getActivity(), gank.getUrl()));
        });
        mBanner.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.latest_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.date:
                if (!isDayHistoryShow) {
                    mTabLayout.setVisibility(View.VISIBLE);
                    isDayHistoryShow = true;
                } else {
                    mTabLayout.setVisibility(View.GONE);
                    isDayHistoryShow = false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void notifyDataSetChanged(GankDailyResult gankDailyResult) {
        setBanner(gankDailyResult.getResults().getWelfare());
        mGanks.clear();
        setData(gankDailyResult.getResults());
        mLatestAdapter.notifyDataSetChanged();
    }

    private void jumpToMoreDay() {
        Intent intent = new Intent(getActivity(), MoreDayActivity.class);
        startActivity(intent);
    }
}
