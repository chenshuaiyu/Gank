package com.example.chen.gank.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.data.bean.GankDailyResult;
import java.util.List;
import androidx.annotation.Nullable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 21:32
 */
public class LatestAdapter extends BaseQuickAdapter<GankDailyResult.GankDailyData, BaseViewHolder> {
    public LatestAdapter(int layoutResId, @Nullable List<GankDailyResult.GankDailyData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankDailyResult.GankDailyData item) {

    }
}
