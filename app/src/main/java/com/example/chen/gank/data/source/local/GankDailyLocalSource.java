package com.example.chen.gank.data.source.local;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.bean.SearchBean;
import com.example.chen.gank.data.source.GankDailySource;
import com.example.chen.gank.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 20:15
 */
public class GankDailyLocalSource implements GankDailySource {
    private static GankDailyLocalSource sGankDailyLocalSource;

    private GankDao mGankDao;
    private AppExecutors mAppExecutors;

    public GankDailyLocalSource(GankDao gankDao, AppExecutors appExecutors) {
        mGankDao = gankDao;
        mAppExecutors = appExecutors;
    }

    public static GankDailyLocalSource getInstance(GankDao gankDao, AppExecutors appExecutors) {
        if (sGankDailyLocalSource == null) {
            synchronized (GankDailyLocalSource.class) {
                if (sGankDailyLocalSource == null) {
                    sGankDailyLocalSource = new GankDailyLocalSource(gankDao, appExecutors);
                }
            }
        }
        return sGankDailyLocalSource;
    }


    @Override
    public MutableLiveData<GankDailyResult> getGankDailyResults() {
        return null;
    }

    @Override
    public MutableLiveData<Day> getDayHistory() {
        return null;
    }

    @Override
    public MutableLiveData<GankDailyResult> getDay(String year, String month, String day) {
        return null;
    }

    @Override
    public void collectGank(Gank gank) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mGankDao.addGank(gank);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<List<Gank>> getGanks() {
        return mGankDao.getGanks();
    }

    @Override
    public void cancelCollect(Gank gank) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mGankDao.deleteGank(gank);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<Gank> isCollected(Gank gank) {
        return mGankDao.existGank(gank.get_id());
    }

    @Override
    public MutableLiveData<SearchBean> search(String keywords, String category, int count, int page) {
        return null;
    }
}
