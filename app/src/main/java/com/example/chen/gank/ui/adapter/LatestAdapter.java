package com.example.chen.gank.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.GankDailyBean;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/15 21:32
 */
public class LatestAdapter extends BaseQuickAdapter<GankDailyBean, BaseViewHolder> {
    private Context mContext;

    public LatestAdapter(int layoutResId, Context context, @Nullable List<GankDailyBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GankDailyBean item) {
        helper.setText(R.id.title, item.getTitle());
        RecyclerView recyclerView = helper.getView(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        GankBeanAdapter adapter = new GankBeanAdapter(R.layout.item_gank_bean, mContext, item.getGanks());
        recyclerView.setAdapter(adapter);
    }
}
