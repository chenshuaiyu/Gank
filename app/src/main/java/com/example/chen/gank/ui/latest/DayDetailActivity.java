package com.example.chen.gank.ui.latest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.chen.gank.R;
import com.example.chen.gank.app.Constants;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.ui.adapter.LatestAdapter;
import com.example.chen.gank.ui.detail.GankDetailActivity;
import com.example.chen.gank.ui.meizhi.MeiZhiDetailActivity;
import com.example.chen.gank.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : chenshuaiyu
 */
public class DayDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private String mDate;
    private GankDailyResult mDailyResult;

    private LatestAdapter mAdapter;
    private List<Gank> mGanks;

    public static Intent newIntent(Context context, String date, GankDailyResult gankDailyResult) {
        Intent intent = new Intent(context, DayDetailActivity.class);
        intent.putExtra(Constants.DATE, date);
        intent.putExtra(Constants.GANKDAILYRESULT, gankDailyResult);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mDate = intent.getStringExtra(Constants.DATE);
        mDailyResult = (GankDailyResult) intent.getSerializableExtra(Constants.GANKDAILYRESULT);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mDate);
        actionBar.setDisplayHomeAsUpEnabled(true);

        initBanner();
        setData();
        mAdapter = new LatestAdapter(this, mGanks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter.setOnClickListener(gank -> {
            startActivity(GankDetailActivity.newIntent(DayDetailActivity.this, gank));
        });
    }

    private void setData() {
        mGanks = new ArrayList<>();
        GankDailyResult.GankDailyData results = mDailyResult.getResults();
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
        List<Gank> welfare = mDailyResult.getResults().getWelfare();
        mBanner.setImages(welfare);
        mBanner.setOnBannerListener(position -> {
            Gank gank = welfare.get(position);
            startActivity(MeiZhiDetailActivity.newIntent(DayDetailActivity.this, gank.getUrl()));
        });
        mBanner.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
}
