package com.example.chen.gank.ui.latest;

import android.os.Bundle;
import android.view.View;

import com.example.chen.gank.R;
import com.example.chen.gank.ui.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 20:57
 */
public class LatestFragment extends BaseFragment {
    private RecyclerView mRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);

    }
}
