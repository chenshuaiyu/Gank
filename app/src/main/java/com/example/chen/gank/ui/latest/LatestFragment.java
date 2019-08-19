package com.example.chen.gank.ui.latest;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.chen.gank.R2;
import com.example.chen.gank.app.Constants;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.ui.main.MainActivity;
import com.example.chen.gank.ui.detail.GankDetailActivity;
import com.example.chen.gank.ui.meizhi.MeiZhiDetailActivity;
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

import butterknife.BindView;

/**
 * @author chenshuaiyu
 * @date : 2019/4/15 20:57
 */
public class LatestFragment extends BaseFragment {

    @BindView(R2.id.banner)
    Banner mBanner;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Gank> mGanks;
    private LatestAdapter mLatestAdapter;
    private LatestViewModel mViewModel;

    private Day mDay;
    private List<Gank> mWelfareList;
    private List<String> mDayList;
    private HashMap<String, GankDailyResult> mResults;

    private int mTablayoutHeight;
    private boolean isDayHistoryShow = false;

    private String mCurrentTitle = "Gank";
    private int defaultSize = 10;

    public String getCurrentTitle() {
        return mCurrentTitle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGanks = new ArrayList<>();
        mWelfareList = new ArrayList<>();
        mDayList = new ArrayList<>();
        mResults = new HashMap<>(14);

        setHasOptionsMenu(true);
        initBanner();

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(LatestViewModel.class);

        mLatestAdapter = new LatestAdapter(getActivity(), mGanks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mLatestAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLatestAdapter.setOnClickListener(gank -> {
            startActivity(GankDetailActivity.newIntent(getActivity(), gank));
        });

        mViewModel.getDayHistory()
                .observe(this, day -> {
                    mDay = day;
                    for (int i = 0; i < defaultSize; i++) {
                        String date = day.getResults().get(i);
                        mDayList.add(date);
                        mResults.put(date, null);

                        String[] split = date.split("-");
                        String s = split[1] + "." + split[2] + "\n" + split[0];
                        mTabLayout.addTab(mTabLayout.newTab().setText(s));
                    }
                    mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_date_white));
                    mTablayoutHeight = mTabLayout.getHeight();
                    tablayoutAnimate(mTabLayout, mTablayoutHeight, 0, 0);

                    mCurrentTitle = mDayList.get(0);
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle(mCurrentTitle);
                    String[] date = mCurrentTitle.split("-");
                    mViewModel.getDay(date[0], date[1], date[2])
                            .observe(this, gankDailyResult -> {
                                        mResults.put(mCurrentTitle, gankDailyResult);
                                        notifyDataSetChanged(gankDailyResult);
                                    }
                            );
                });

        tabSelect();
    }

    private void tabSelect() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            private int lastPosition;

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() < defaultSize) {
                    mCurrentTitle = mDayList.get(tab.getPosition());
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle(mCurrentTitle);
                    if (mResults.get(mCurrentTitle) != null) {
                        GankDailyResult result = mResults.get(mCurrentTitle);
                        notifyDataSetChanged(result);
                    } else {
                        String[] split = mCurrentTitle.split("-");
                        mViewModel.getDay(split[0], split[1], split[2])
                                .observe(LatestFragment.this, gankDailyResult -> {
                                    mResults.put(mCurrentTitle, gankDailyResult);
                                    notifyDataSetChanged(gankDailyResult);
                                });
                    }
                } else {
                    jumpToMoreDay();
                    mTabLayout.getTabAt(lastPosition).select();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                lastPosition = tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
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

    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setDelayTime(2500);
        mBanner.setOnBannerListener(position -> {
            Gank gank = mWelfareList.get(position);
            startActivity(MeiZhiDetailActivity.newIntent(getActivity(), gank.getUrl()));
        });
    }

    private void setBanner(List<Gank> welfare) {
        mWelfareList.clear();
        if (welfare != null && welfare.size() != 0) {
            mWelfareList.addAll(welfare);
        }
        mBanner.setImages(mWelfareList);
        mBanner.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_latest, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.date:
                if (!isDayHistoryShow) {
                    tablayoutAnimate(mTabLayout, 0, mTablayoutHeight, 300);
                    isDayHistoryShow = true;
                } else {
                    tablayoutAnimate(mTabLayout, mTablayoutHeight, 0, 300);
                    isDayHistoryShow = false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void tablayoutAnimate(View view, int start, int end, long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        IntEvaluator intEvaluator = new IntEvaluator();
        valueAnimator.addUpdateListener(animation -> {
            view.getLayoutParams().height = intEvaluator.evaluate(animation.getAnimatedFraction(), start, end);
            view.requestLayout();
        });
        valueAnimator.setDuration(duration).start();
    }

    private void notifyDataSetChanged(GankDailyResult gankDailyResult) {
        setBanner(gankDailyResult.getResults().getWelfare());
        mGanks.clear();
        setData(gankDailyResult.getResults());
        mLatestAdapter.notifyDataSetChanged();
    }

    private void jumpToMoreDay() {
        Intent intent = new Intent(getActivity(), MoreDayActivity.class);
        intent.putExtra(Constants.DAY, mDay);
        startActivity(intent);
    }
}
