package com.example.chen.gank.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.chen.gank.data.bean.Gank;
import com.youth.banner.loader.ImageLoader;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/18 20:27
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        //Glide 加载图片
        Gank gank = (Gank) o;
        Glide.with(context).load(gank.getUrl()).into(imageView);
    }
}
