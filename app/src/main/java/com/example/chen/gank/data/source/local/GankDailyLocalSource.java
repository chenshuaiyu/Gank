package com.example.chen.gank.data.source.local;

import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.GankDailySource;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:15
 */
public class GankDailyLocalSource implements GankDailySource {
    private static GankDailyLocalSource sGankDailyLocalSource;

    private GankDailyLocalSource() {
    }

    public static GankDailyLocalSource getInstance() {
        if (sGankDailyLocalSource == null){
            synchronized (GankDailyLocalSource.class){
                if (sGankDailyLocalSource == null)
                    sGankDailyLocalSource = new GankDailyLocalSource();
            }
        }
        return sGankDailyLocalSource;
    }


    @Override
    public MutableLiveData<GankDailyResult> getGankDailyResults() {
        return null;
    }
}
