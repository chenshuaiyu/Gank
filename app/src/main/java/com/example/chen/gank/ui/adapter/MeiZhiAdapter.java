package com.example.chen.gank.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/16 21:36
 */
public class MeiZhiAdapter extends BaseQuickAdapter<Gank, BaseViewHolder> {
    private Context mContext;

    public MeiZhiAdapter(int layoutResId, Context context, @Nullable List<Gank> data) {
        super(layoutResId, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, Gank item) {
        ImageView image = helper.getView(R.id.image);
        Glide.with(mContext).load(item.getUrl()).into(image);
    }
}
