package com.example.chen.gank.ui.meizhi;

import com.example.chen.gank.data.bean.GankFilterResult;
import com.example.chen.gank.data.source.GankFilterRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/16 21:15
 */
public class MeiZhiViewModel extends ViewModel {
    private GankFilterRepository mGankFilterRepository;

    public MeiZhiViewModel(GankFilterRepository gankFilterRepository) {
        mGankFilterRepository = gankFilterRepository;
    }

    public MutableLiveData<GankFilterResult> getGankFilterResults(int count, int page) {
        return mGankFilterRepository.getGankFilterResults("福利", count, page);
    }
}
