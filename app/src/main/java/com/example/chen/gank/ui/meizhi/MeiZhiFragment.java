package com.example.chen.gank.ui.meizhi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chen.gank.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.ui.MeiZhiActivity;
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

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/16 21:13
 */
public class MeiZhiFragment extends BaseFragment {
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<Gank> mGanks;
    private MeiZhiAdapter mAdapter;
    private MeiZhiViewModel mViewModel;

    private int curPage = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_meizhi;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            Gank gank = mGanks.get(position);
            if (!TextUtils.isEmpty(gank.getUrl())) {
                Intent intent = MeiZhiActivity.newIntent(getActivity(), gank.getUrl());
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
}
