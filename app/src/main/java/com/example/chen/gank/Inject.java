package com.example.chen.gank;

import com.example.chen.gank.data.source.GankDailyRepository;
import com.example.chen.gank.data.source.GankFilterRepository;
import com.example.chen.gank.data.source.local.GankDailyLocalSource;
import com.example.chen.gank.data.source.local.GankDatabase;
import com.example.chen.gank.data.source.local.GankFilterLocalSource;
import com.example.chen.gank.data.source.remote.GankDailyRemoteSource;
import com.example.chen.gank.data.source.remote.GankFilterRemoteSource;
import com.example.chen.gank.ui.ViewModelFactory;
import com.example.chen.gank.utils.AppExecutors;

import androidx.lifecycle.ViewModelProvider;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/13 10:08
 */
public class Inject {
    private static AppExecutors sAppExecutors = new AppExecutors();

    private static ViewModelFactory mViewModelFactory;

    public static ViewModelProvider.Factory getModelFactory() {
        if (mViewModelFactory == null)
            mViewModelFactory = new ViewModelFactory(
                    GankDailyRepository.getInstance(GankDailyLocalSource.getInstance(GankDatabase.getInstance().ganksDao(), sAppExecutors), GankDailyRemoteSource.getInstance()),
                    GankFilterRepository.getInstance(GankFilterLocalSource.getInstance(), GankFilterRemoteSource.getInstance())
            );
        return mViewModelFactory;
    }
}
