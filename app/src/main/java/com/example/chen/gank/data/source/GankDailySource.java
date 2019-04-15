package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.GankDailyResult;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:07
 */
public interface GankDailySource {
    MutableLiveData<GankDailyResult> getGankDailyResults();
}
