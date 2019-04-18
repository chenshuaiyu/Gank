package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:07
 */
public interface GankDailySource {

    interface GetGanksCallback{

        void onGanksLoaded();

        void onDataNotAvailable();
    }

    interface AddGankCallback{

        void onGanksAdded();

        void onDataNotAvailable();
    }



    MutableLiveData<GankDailyResult> getGankDailyResults();

    MutableLiveData<Day> getDayHistory();

    MutableLiveData<GankDailyResult> getDay(String year, String month, String day);

    void collectGank(Gank gank);

    MutableLiveData<List<Gank>> getGanks();
}
