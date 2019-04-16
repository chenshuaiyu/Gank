package com.example.chen.gank.data.source.remote;

import com.example.chen.gank.data.api.Api;
import com.example.chen.gank.data.api.RetrofitClient;
import com.example.chen.gank.data.bean.GankFilterResult;
import com.example.chen.gank.data.source.GankFilterSource;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:13
 */
public class GankFilterRemoteSource implements GankFilterSource {
    private static GankFilterRemoteSource sGankFilterRemoteSource;

    private Api mApi;

    private GankFilterRemoteSource(Api api) {
        mApi = api;
    }

    public static GankFilterRemoteSource getInstance() {
        if (sGankFilterRemoteSource == null) {
            synchronized (GankFilterRemoteSource.class) {
                if (sGankFilterRemoteSource == null)
                    sGankFilterRemoteSource = new GankFilterRemoteSource(RetrofitClient.createService());
            }
        }
        return sGankFilterRemoteSource;
    }

    @Override
    public MutableLiveData<GankFilterResult> getGankFilterResults(String filterType, int count, int page) {
        MutableLiveData<GankFilterResult> liveData = new MutableLiveData<>();
        mApi.gankFilter(filterType, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankFilterResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankFilterResult gankFilterResult) {
                        liveData.setValue(gankFilterResult);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }
}
