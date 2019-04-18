package com.example.chen.gank.ui.latest;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.GankDailyRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/17 21:44
 */
public class MoreDayViewModel extends ViewModel {
    private GankDailyRepository mGankDailyRepository;

    public MoreDayViewModel(GankDailyRepository gankDailyRepository) {
        mGankDailyRepository = gankDailyRepository;
    }

    public MutableLiveData<GankDailyResult> getDay(String year, String month, String day) {
        return mGankDailyRepository.getDay(year, month, day);
    }

}
