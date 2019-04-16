package com.example.chen.gank.data.source.local;

import com.example.chen.gank.data.bean.GankFilterResult;
import com.example.chen.gank.data.source.GankFilterSource;
import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:15
 */
public class GankFilterLocalSource implements GankFilterSource {
    private static GankFilterLocalSource sGankFilterLocalSource;

    private GankFilterLocalSource() {
    }

    public static GankFilterLocalSource getInstance() {
        if (sGankFilterLocalSource == null){
            synchronized (GankFilterLocalSource.class){
                if (sGankFilterLocalSource == null)
                    sGankFilterLocalSource = new GankFilterLocalSource();
            }
        }
        return sGankFilterLocalSource;
    }

    @Override
    public MutableLiveData<GankFilterResult> getGankFilterResults(String filterType, int count, int page) {
        return null;
    }
}
