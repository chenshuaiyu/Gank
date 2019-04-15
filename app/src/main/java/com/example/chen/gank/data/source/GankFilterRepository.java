package com.example.chen.gank.data.source;

import com.example.chen.gank.data.source.local.GankFilterLocalSource;
import com.example.chen.gank.data.source.remote.GankFilterRemoteSource;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:09
 */
public class GankFilterRepository implements GankFilterSource {
    private GankFilterLocalSource mFilterLocalSource;
    private GankFilterRemoteSource mFilterRemoteSource;

    public GankFilterRepository(GankFilterLocalSource filterLocalSource, GankFilterRemoteSource filterRemoteSource) {
        mFilterLocalSource = filterLocalSource;
        mFilterRemoteSource = filterRemoteSource;
    }



}
