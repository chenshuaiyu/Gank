package com.example.chen.gank.ui.meizhi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.chen.gank.R2;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.ui.adapter.MeiZhiAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/16 21:13
 */
public class MeiZhiFragment extends BaseFragment {

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Gank> mGanks;
    private MeiZhiAdapter mAdapter;
    private MeiZhiViewModel mViewModel;

    private GridLayoutManager gridLayoutManager1;
    private GridLayoutManager gridLayoutManager2;

    private int curPage = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_meizhi;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        mGanks = new ArrayList<>();
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MeiZhiViewModel.class);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            curPage = 1;
            mGanks.clear();
            getData();
            refreshLayout.finishRefresh(1500);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            getData();
            refreshLayout.finishLoadMore(1500);
        });

        mAdapter = new MeiZhiAdapter(R.layout.item_image, getActivity(), mGanks);
        gridLayoutManager1 = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager2 = new GridLayoutManager(getActivity(), 2);

        mRecyclerView.setLayoutManager(gridLayoutManager2);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            Gank gank = mGanks.get(position);
            if (!TextUtils.isEmpty(gank.getUrl())) {
                Intent intent = MeiZhiDetailActivity.newIntent(getActivity(), gank.getUrl());
                startActivity(intent);
            }
        });

        getData();
    }

    private void getData() {
        mViewModel.getGankFilterResults(20, curPage++)
                .observe(this, gankFilterResult -> {
                    mGanks.addAll(gankFilterResult.getResults());
                    mAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_meizhi, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_column:
                if (mRecyclerView.getLayoutManager() == gridLayoutManager1) {
                    mRecyclerView.setLayoutManager(gridLayoutManager2);
                } else {
                    mRecyclerView.setLayoutManager(gridLayoutManager1);
                }
                break;
            default:
                break;
        }
        return true;
    }
}
