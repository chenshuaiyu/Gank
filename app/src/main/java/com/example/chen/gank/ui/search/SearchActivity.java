package com.example.chen.gank.ui.search;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chen.gank.R;
import com.example.chen.gank.app.Constants;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.SearchBean;
import com.example.chen.gank.ui.adapter.SearchAdapter;
import com.example.chen.gank.ui.detail.GankDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : chenshuaiyu
 */
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_search)
    EditText mEditText;
    @BindView(R.id.sp_filter)
    AppCompatSpinner mFilterSpinner;

    private String type;
    private String keywords;
    private int defaultCount = 10;
    private int curPage = 1;

    private List<SearchBean.ResultsBean> mResultsBeans;

    private ArrayAdapter mArrayAdapter;
    private SearchAdapter mSearchAdapter;
    private SearchViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(SearchViewModel.class);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initSpinner();
        initRecyclerView();

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            curPage = 1;
            mViewModel.search(keywords, type, defaultCount, curPage)
                    .observe(this, searchBean -> {
                        mResultsBeans.clear();
                        setData(searchBean);
                    });
            refreshLayout.finishRefresh(1500);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mViewModel.search(keywords, type, defaultCount, ++curPage)
                    .observe(this, this::setData);
            refreshLayout.finishLoadMore(1500);
        });
    }

    private void setData(SearchBean searchBean) {
        if (searchBean != null && !searchBean.isError()) {
            mResultsBeans.addAll(searchBean.getResults());
            if (mResultsBeans.size() == 0) {
                Toast.makeText(this, "没有更多干货", Toast.LENGTH_SHORT).show();
            }
            mSearchAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "搜索发生错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void initSpinner() {
        mArrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.item_text, Constants.FILTER_TYPE);
        mFilterSpinner.setAdapter(mArrayAdapter);
        mFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = Constants.FILTER_TYPE[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initRecyclerView() {
        mResultsBeans = new ArrayList<>();
        mSearchAdapter = new SearchAdapter(R.layout.item_gank, mResultsBeans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchAdapter);
        mSearchAdapter.setOnItemClickListener((adapter, view, position) -> {
            Gank gank = createGank(mResultsBeans.get(position));
            startActivity(GankDetailActivity.newIntent(SearchActivity.this, gank));
        });
    }

    private Gank createGank(SearchBean.ResultsBean resultsBean) {
        Gank gank = new Gank();
        gank.setDesc(resultsBean.getDesc());
        gank.set_id(resultsBean.getGanhuo_id());
        gank.setPublishedAt(resultsBean.getPublishedAt());
        gank.setType(resultsBean.getType());
        gank.setUrl(resultsBean.getUrl());
        gank.setWho(resultsBean.getWho());
        return gank;
    }

    private void search() {
        keywords = mEditText.getText().toString();
        if (!TextUtils.isEmpty(keywords)) {
            mViewModel.search(keywords, type, defaultCount, curPage)
                    .observe(this, searchBean -> {
                        mResultsBeans.clear();
                        setData(searchBean);
                    });
        } else {
            Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.search:
                mEditText.clearFocus();
                search();
                break;
            default:
                break;
        }
        return true;
    }
}
