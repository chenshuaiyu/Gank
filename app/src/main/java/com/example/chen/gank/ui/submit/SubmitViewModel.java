package com.example.chen.gank.ui.submit;

import androidx.lifecycle.ViewModel;

import com.example.chen.gank.data.source.GankDailyRepository;

/**
 * @author : chenshuaiyu
 * @date : 2019/8/17 17:09
 */
public class SubmitViewModel extends ViewModel {
    private GankDailyRepository mDailyRepository;

    public SubmitViewModel(GankDailyRepository dailyRepository) {
        mDailyRepository = dailyRepository;
    }

    public void submit(String address, String desc, String authorId,String type){

    }
}
