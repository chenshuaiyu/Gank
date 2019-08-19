package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.GankFilterResult;
import com.example.chen.gank.data.source.local.GankFilterLocalSource;
import com.example.chen.gank.data.source.remote.GankFilterRemoteSource;

import androidx.lifecycle.MutableLiveData;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 20:09
 */
public class GankFilterRepository implements GankFilterSource {
    private static GankFilterRepository sGankFilterRepository;

    private GankFilterLocalSource mFilterLocalSource;
    private GankFilterRemoteSource mFilterRemoteSource;

    public GankFilterRepository(GankFilterLocalSource filterLocalSource, GankFilterRemoteSource filterRemoteSource) {
        mFilterLocalSource = filterLocalSource;
        mFilterRemoteSource = filterRemoteSource;
    }

    public static GankFilterRepository getInstance(GankFilterLocalSource gankFilterLocalSource, GankFilterRemoteSource gankFilterRemoteSource) {
        if (sGankFilterRepository == null) {
            synchronized (GankFilterRepository.class) {
                if (sGankFilterRepository == null) {
                    sGankFilterRepository = new GankFilterRepository(gankFilterLocalSource, gankFilterRemoteSource);
                }
            }
        }
        return sGankFilterRepository;
    }

    @Override
    public MutableLiveData<GankFilterResult> getGankFilterResults(String filterType, int count, int page) {
        return mFilterRemoteSource.getGankFilterResults(filterType, count, page);
    }
}
