package com.example.chen.gank.ui.latest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.example.chen.gank.Constants;
import com.example.chen.gank.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyBean;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.ui.adapter.LatestAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.example.chen.gank.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import java.util.ArrayList;
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

    private RecyclerView mRecyclerView;
    private List<GankDailyBean> mGankDailyBeans;
    private LatestAdapter mLatestAdapter;
    private LatestViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mGankDailyBeans = new ArrayList<>();

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(LatestViewModel.class);

        mLatestAdapter = new LatestAdapter(R.layout.item_gank_daily_data, getActivity(), mGankDailyBeans);
        View bannerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner, null);
        mBanner = bannerLayout.findViewById(R.id.banner);
        mLatestAdapter.setHeaderView(bannerLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mLatestAdapter);

        mViewModel.getGankDailyResults()
                .observe(this, gankDailyResult -> {
                            setBanner(gankDailyResult.getResults().getWelfare());
                            setData(gankDailyResult.getResults());
                            mLatestAdapter.notifyDataSetChanged();
                        }
                );
    }

    private void setData(GankDailyResult.GankDailyData results) {
        for (String type : Constants.FILTER_TYPE) {
            mGankDailyBeans.add(new GankDailyBean(type, results.getAndroid()));
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

        });
        mBanner.start();
    }
}
