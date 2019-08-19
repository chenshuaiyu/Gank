package com.example.chen.gank.ui.main;

import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.source.GankDailyRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/15 19:21
 */
public class MainViewModel extends ViewModel {
    private GankDailyRepository mGankDailyRepository;

    public MainViewModel(GankDailyRepository gankDailyRepository) {
        mGankDailyRepository = gankDailyRepository;
    }
}
