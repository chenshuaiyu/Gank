package com.example.chen.gank.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chen.gank.data.bean.SearchBean;
import com.example.chen.gank.data.source.GankDailyRepository;

/**
 * @author : chenshuaiyu
 * @date : 2019/8/17 17:33
 */
public class SearchViewModel extends ViewModel {
    private GankDailyRepository mDailyRepository;

    public SearchViewModel(GankDailyRepository dailyRepository) {
        mDailyRepository = dailyRepository;
    }

    public MutableLiveData<SearchBean> search(String keywords, String category, int count, int page) {
        return mDailyRepository.search(keywords, category, count, page);
    }
}
