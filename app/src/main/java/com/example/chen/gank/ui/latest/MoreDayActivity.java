package com.example.chen.gank.ui.latest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chen.gank.Constants;
import com.example.chen.gank.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.ui.adapter.MoreDayAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MoreDayActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    private MoreDayViewModel mViewModel;

    private Day mDay;
    private MoreDayAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_day);
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MoreDayViewModel.class);

        mDay = (Day) getIntent().getSerializableExtra(Constants.DAY);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.history);

        mAdapter = new MoreDayAdapter(R.layout.item_day, mDay.getResults().subList(0, 10));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {

            refreshLayout.finishRefresh(1500);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {

            refreshLayout.finishLoadMore(1500);
        });

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
