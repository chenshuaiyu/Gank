package com.example.chen.gank;

import com.example.chen.gank.data.source.GankDailyRepository;
import com.example.chen.gank.data.source.local.GankDailyLocalSource;
import com.example.chen.gank.data.source.remote.GankDailyRemoteSource;
import com.example.chen.gank.ui.ViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/13 10:08
 */
public class Inject {
    private static ViewModelFactory mViewModelFactory;

    public static ViewModelProvider.Factory getModelFactory() {
        if (mViewModelFactory == null)
            mViewModelFactory = new ViewModelFactory(GankDailyRepository.getInstance(GankDailyLocalSource.getInstance(), GankDailyRemoteSource.getInstance()));
        return mViewModelFactory;
    }
}
