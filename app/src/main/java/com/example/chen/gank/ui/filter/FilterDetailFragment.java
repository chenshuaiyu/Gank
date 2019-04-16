package com.example.chen.gank.ui.filter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.chen.gank.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.ui.adapter.GankBeanAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/16 16:08
 */
@SuppressLint("ValidFragment")
public class FilterDetailFragment extends BaseFragment {
    private String filterType;

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<Gank> mGanks;
    private GankBeanAdapter mAdapter;
    private FilterViewModel mViewModel;

    public FilterDetailFragment(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterType() {
        return filterType;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_filter_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(FilterViewModel.class);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(1500);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore(1500);
        });

        mGanks = new ArrayList<>();
        mAdapter = new GankBeanAdapter(R.layout.item_gank_bean, getActivity(), mGanks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mViewModel.getGankFilterResults(filterType, 10, 1)
                .observe(getActivity(), gankFilterResult -> {
                    mGanks.addAll(gankFilterResult.getResults());
                    mAdapter.notifyDataSetChanged();
                });
    }
}
