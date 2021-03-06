package com.example.chen.gank.ui.latest;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.GankDailyRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/16 13:55
 */
public class LatestViewModel extends ViewModel {
    private GankDailyRepository mGankDailyRepository;

    public LatestViewModel(GankDailyRepository gankDailyRepository) {
        mGankDailyRepository = gankDailyRepository;
    }

    public MutableLiveData<GankDailyResult> getGankDailyResults() {
        return mGankDailyRepository.getGankDailyResults();
    }

    public MutableLiveData<Day> getDayHistory() {
        return mGankDailyRepository.getDayHistory();
    }

    public MutableLiveData<GankDailyResult> getDay(String year, String month, String day) {
        return mGankDailyRepository.getDay(year, month, day);
    }
}
