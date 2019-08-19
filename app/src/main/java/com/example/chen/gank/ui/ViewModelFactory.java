package com.example.chen.gank.ui;

import com.example.chen.gank.data.source.GankDailyRepository;
import com.example.chen.gank.data.source.GankFilterRepository;
import com.example.chen.gank.ui.main.MainViewModel;
import com.example.chen.gank.ui.collect.CollectViewModel;
import com.example.chen.gank.ui.detail.GankDetailViewModel;
import com.example.chen.gank.ui.filter.FilterViewModel;
import com.example.chen.gank.ui.latest.LatestViewModel;
import com.example.chen.gank.ui.latest.MoreDayViewModel;
import com.example.chen.gank.ui.meizhi.MeiZhiViewModel;
import com.example.chen.gank.ui.search.SearchViewModel;
import com.example.chen.gank.ui.submit.SubmitViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 9:51
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GankDailyRepository mGankDailyRepository;
    private GankFilterRepository mGankFilterRepository;

    public ViewModelFactory(GankDailyRepository gankDailyRepository, GankFilterRepository gankFilterRepository) {
        mGankDailyRepository = gankDailyRepository;
        mGankFilterRepository = gankFilterRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T t = null;
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            t = (T) new MainViewModel(mGankDailyRepository);
        } else if (modelClass.isAssignableFrom(LatestViewModel.class)) {
            t = (T) new LatestViewModel(mGankDailyRepository);
        } else if (modelClass.isAssignableFrom(FilterViewModel.class)) {
            t = (T) new FilterViewModel(mGankFilterRepository);
        } else if (modelClass.isAssignableFrom(MeiZhiViewModel.class)) {
            t = (T) new MeiZhiViewModel(mGankFilterRepository);
        } else if (modelClass.isAssignableFrom(MoreDayViewModel.class)) {
            t = (T) new MoreDayViewModel(mGankDailyRepository);
        } else if (modelClass.isAssignableFrom(GankDetailViewModel.class)) {
            t = (T) new GankDetailViewModel(mGankDailyRepository);
        } else if (modelClass.isAssignableFrom(CollectViewModel.class)) {
            t = (T) new CollectViewModel(mGankDailyRepository);
        } else if (modelClass.isAssignableFrom(SubmitViewModel.class)) {
            t = (T) new SubmitViewModel(mGankDailyRepository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            t = (T) new SearchViewModel(mGankDailyRepository);
        }
        return t;
    }
}
