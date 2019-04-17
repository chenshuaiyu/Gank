package com.example.chen.gank.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/16 14:52
 */
public class FilterDetailAdapter extends BaseQuickAdapter<Gank, BaseViewHolder> {
    private Context mContext;

    public FilterDetailAdapter(int layoutResId, Context context, @Nullable List<Gank> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Gank item) {
        helper.setText(R.id.article_title, item.getDesc());
        helper.setText(R.id.author, item.getWho());
        helper.setText(R.id.date, item.getPublishedAt().substring(0, item.getPublishedAt().indexOf("T")));
        ImageView image = helper.getView(R.id.image);
        if (item.getImages() != null && item.getImages().size() != 0) {
            image.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getImages().get(0)).into(image);
        }
    }
}
