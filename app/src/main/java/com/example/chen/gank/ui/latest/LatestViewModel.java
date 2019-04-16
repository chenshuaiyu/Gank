package com.example.chen.gank.ui.latest;

import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.GankDailyRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/16 13:55
 */
public class LatestViewModel extends ViewModel {
    private GankDailyRepository mGankDailyRepository;

    public LatestViewModel(GankDailyRepository gankDailyRepository) {
        mGankDailyRepository = gankDailyRepository;
    }

    public MutableLiveData<GankDailyResult> getGankDailyResults(){
        return mGankDailyRepository.getGankDailyResults();
    }
}
