package com.example.chen.gank.ui.filter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.chen.gank.app.Constants;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.ui.activity.GankDetailActivity;
import com.example.chen.gank.ui.adapter.FilterDetailAdapter;
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
    private FilterDetailAdapter mAdapter;
    private FilterViewModel mViewModel;

    private int curPage = 1;

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
            curPage = 1;
            mGanks.clear();
            getData();
            refreshLayout.finishRefresh(1500);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            getData();
            refreshLayout.finishLoadMore(1500);
        });

        mGanks = new ArrayList<>();
        mAdapter = new FilterDetailAdapter(R.layout.item_gank, getActivity(), mGanks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            Intent intent = new Intent(getActivity(), GankDetailActivity.class);
            intent.putExtra(Constants.GANK, mGanks.get(position));
            startActivity(intent);
        });

        getData();
    }

    private void getData(){
        mViewModel.getGankFilterResults(filterType, 10, curPage++)
                .observe(getActivity(), gankFilterResult -> {
                    mGanks.addAll(gankFilterResult.getResults());
                    mAdapter.notifyDataSetChanged();
                });
    }
}
