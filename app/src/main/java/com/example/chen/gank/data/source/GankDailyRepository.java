package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.local.GankDailyLocalSource;
import com.example.chen.gank.data.source.remote.GankDailyRemoteSource;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:09
 */
public class GankDailyRepository implements GankDailySource {
    private static GankDailyRepository sGankDailyRepository;

    private GankDailyLocalSource mDailyLocalSource;
    private GankDailyRemoteSource mDailyRemoteSource;

    private GankDailyRepository(GankDailyLocalSource dailyLocalSource, GankDailyRemoteSource dailyRemoteSource) {
        mDailyLocalSource = dailyLocalSource;
        mDailyRemoteSource = dailyRemoteSource;
    }

    public static GankDailyRepository getInstance(GankDailyLocalSource dailyLocalSource, GankDailyRemoteSource dailyRemoteSource){
        if (sGankDailyRepository == null){
            synchronized (GankDailyRepository.class){
                if (sGankDailyRepository == null)
                    sGankDailyRepository = new GankDailyRepository(dailyLocalSource, dailyRemoteSource);
            }
        }
        return sGankDailyRepository;
    }

    @Override
    public MutableLiveData<GankDailyResult> getGankDailyResults() {
        return mDailyRemoteSource.getGankDailyResults();
    }

    @Override
    public MutableLiveData<Day> getDayHistory() {
        return mDailyRemoteSource.getDayHistory();
    }

    @Override
    public MutableLiveData<GankDailyResult> getDay(String year, String month, String day) {
        return mDailyRemoteSource.getDay(year, month, day);
    }

    @Override
    public void collectGank(Gank gank) {
        mDailyLocalSource.collectGank(gank);
    }

    @Override
    public MutableLiveData<List<Gank>> getGanks() {
        return mDailyLocalSource.getGanks();
    }
}
