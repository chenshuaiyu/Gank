package com.example.chen.gank.ui.collect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.source.GankDailyRepository;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/16 22:26
 */
public class CollectViewModel extends ViewModel {
    private GankDailyRepository mDailyRepository;

    public CollectViewModel(GankDailyRepository dailyRepository) {
        mDailyRepository = dailyRepository;
    }

    public LiveData<List<Gank>> getCollectedGanks(){
        return mDailyRepository.getGanks();
    }
}
