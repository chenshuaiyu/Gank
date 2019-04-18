package com.example.chen.gank.data.source.local;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.GankDailySource;
import com.example.chen.gank.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:15
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
                if (sGankDailyLocalSource == null)
                    sGankDailyLocalSource = new GankDailyLocalSource(gankDao, appExecutors);
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
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                MutableLiveData<List<Gank>> ganks = mGankDao.getGanks();
//
//                mAppExecutors.mainThread().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        ganks.observe(, );
//                    }
//                });
//            }
//        }

        mGankDao.addGank(gank);
    }

    @Override
    public MutableLiveData<List<Gank>> getGanks() {
        return mGankDao.getGanks();
    }

}
