package com.example.chen.gank.ui.latest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.chen.gank.app.Constants;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.ui.adapter.MoreDayAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : chenshuaiyu
 */
public class MoreDayActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private MoreDayViewModel mViewModel;

    private Day mDay;
    private MoreDayAdapter mAdapter;

    /**
     * List<String> 0位置存储date，1位置存储image
     */
    private List<List<String>> mDatas;
    private HashMap<String, GankDailyResult> mDailyResultHashMap;

    private int num = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_day);
        ButterKnife.bind(this);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MoreDayViewModel.class);

        mDay = (Day) getIntent().getSerializableExtra(Constants.DAY);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.history);

        mDailyResultHashMap = new HashMap<>();
        mDatas = new ArrayList<>();
        mAdapter = new MoreDayAdapter(R.layout.item_day, this, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            String key = mDatas.get(position).get(0);
            GankDailyResult result = mDailyResultHashMap.get(key);
            if (result != null && !result.isError()) {
                startActivity(DayDetailActivity.newIntent(MoreDayActivity.this, key, result));
            }
        });

        for (int i = 0; i < num; i++) {
            setData(i);
        }
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            num = 10;
            mDatas.clear();
            for (int i = 0; i < num; i++) {
                setData(i);
            }
            refreshLayout.finishRefresh(1500);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            for (int i = num; i < num + 10; i++) {
                setData(i);
            }
            num += 10;
            refreshLayout.finishLoadMore(1500);
        });
    }

    private void setData(int i) {
        String s = mDay.getResults().get(i);
        List<String> list = new ArrayList<>();
        list.add(s);
        mDatas.add(list);
        String[] date = s.split("-");
        int finalI = i;
        mViewModel.getDay(date[0], date[1], date[2])
                .observe(this, gankDailyResult -> {
                            mDailyResultHashMap.put(mDatas.get(finalI).get(0), gankDailyResult);
                            List<Gank> welfare = gankDailyResult.getResults().getWelfare();
                            if (welfare != null && welfare.size() > 0) {
                                mDatas.get(finalI).add(welfare.get(0).getUrl());
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
