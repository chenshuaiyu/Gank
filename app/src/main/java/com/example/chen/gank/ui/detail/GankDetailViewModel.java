package com.example.chen.gank.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.source.GankDailyRepository;

/**
 * @author : chenshuaiyu
 * @date : 2019/8/16 10:19
 */
public class GankDetailViewModel extends ViewModel {
    private GankDailyRepository mDailyRepository;

    public GankDetailViewModel(GankDailyRepository dailyRepository) {
        mDailyRepository = dailyRepository;
    }

    public void collect(Gank gank) {
        mDailyRepository.collectGank(gank);
    }

    public void cancelCollect(Gank gank) {
        mDailyRepository.cancelCollect(gank);
    }

    public LiveData<Gank> isCollected(Gank gank) {
        return mDailyRepository.isCollected(gank);
    }
}
