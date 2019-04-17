package com.example.chen.gank.ui.latest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chen.gank.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MoreDayActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_day);
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.history);

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
