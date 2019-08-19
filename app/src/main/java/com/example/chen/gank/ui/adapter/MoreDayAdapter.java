package com.example.chen.gank.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.gank.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/18 9:08
 */
public class MoreDayAdapter extends BaseQuickAdapter<List<String>, BaseViewHolder> {
    private Context mContext;

    public MoreDayAdapter(int layoutResId, Context context, @Nullable List<List<String>> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, List<String> item) {
        helper.setText(R.id.date, item.get(0));
        if (item.size() > 1) {
            Glide.with(mContext)
                    .load(item.get(1))
                    .into((ImageView) helper.getView(R.id.image));
        }
    }
}
