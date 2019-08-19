package com.example.chen.gank.ui.collect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chen.gank.R;
import com.example.chen.gank.app.Constants;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.ui.adapter.GankItemAdapter;
import com.example.chen.gank.ui.base.BaseFragment;
import com.example.chen.gank.ui.detail.GankDetailActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/16 22:25
 */
public class CollectFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private CollectViewModel mViewModel;

    private GankItemAdapter mAdapter;
    private List<Gank> mGanks;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(CollectViewModel.class);

        mGanks = new ArrayList<>();
        mAdapter = new GankItemAdapter(R.layout.item_gank, getActivity(), mGanks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            startActivity(GankDetailActivity.newIntent(getActivity(), mGanks.get(position)));
        });

        mViewModel.getCollectedGanks()
                .observe(this, ganks -> {
                    mGanks.clear();
                    mGanks.addAll(ganks);
                    mAdapter.notifyDataSetChanged();
                });
    }
}
