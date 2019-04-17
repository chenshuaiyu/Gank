package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.GankDailyResult;

import androidx.lifecycle.MutableLiveData;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:07
 */
public interface GankDailySource {

    MutableLiveData<GankDailyResult> getGankDailyResults();

    MutableLiveData<Day> getDayHistory();

    MutableLiveData<GankDailyResult> getDay(String year, String month, String day);
}
