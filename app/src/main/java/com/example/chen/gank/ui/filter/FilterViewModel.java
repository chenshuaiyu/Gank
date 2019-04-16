package com.example.chen.gank.ui.filter;

import com.example.chen.gank.data.bean.GankFilterResult;
import com.example.chen.gank.data.source.GankFilterRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/16 15:37
 */
public class FilterViewModel extends ViewModel {
    private GankFilterRepository mGankFilterRepository;

    public FilterViewModel(GankFilterRepository gankFilterRepository) {
        mGankFilterRepository = gankFilterRepository;
    }

    public MutableLiveData<GankFilterResult> getGankFilterResults(String filterType, int count, int page) {
        return mGankFilterRepository.getGankFilterResults(filterType, count, page);
    }
}
