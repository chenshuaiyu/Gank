package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.GankFilterResult;

import androidx.lifecycle.MutableLiveData;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 20:07
 */
public interface GankFilterSource {
    /**
     * 获取 Gank 各类别数据
     *
     * @param filterType
     * @param count
     * @param page
     * @return
     */
    MutableLiveData<GankFilterResult> getGankFilterResults(String filterType, int count, int page);
}
