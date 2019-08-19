package com.example.chen.gank.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.SearchBean;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/8/18 18:45
 */
public class SearchAdapter extends BaseQuickAdapter<SearchBean.ResultsBean, BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<SearchBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchBean.ResultsBean item) {
        helper.setText(R.id.article_title, item.getDesc());
        helper.setText(R.id.author, item.getWho());
        helper.setText(R.id.date, item.getPublishedAt().substring(0, item.getPublishedAt().indexOf("T")));
    }
}
