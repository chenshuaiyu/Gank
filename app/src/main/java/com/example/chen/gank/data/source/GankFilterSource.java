package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.GankFilterResult;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:07
 */
public interface GankFilterSource {
    MutableLiveData<GankFilterResult> getGankFilterResults(String filterType, int count, int page);
}
