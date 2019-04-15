package com.example.chen.gank.ui;

import com.example.chen.gank.data.source.GankDailyRepository;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 9:51
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GankDailyRepository mGankDailyRepository;

    public ViewModelFactory(GankDailyRepository gankDailyRepository) {
        mGankDailyRepository = gankDailyRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T t = null;
        if (modelClass.isAssignableFrom(MainViewModel.class))
            t = (T) new MainViewModel(mGankDailyRepository);
        return t;
    }
}
