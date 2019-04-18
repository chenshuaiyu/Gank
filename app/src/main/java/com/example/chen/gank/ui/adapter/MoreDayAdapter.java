package com.example.chen.gank.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.R;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/18 9:08
 */
public class MoreDayAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MoreDayAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.date, item);
    }
}
