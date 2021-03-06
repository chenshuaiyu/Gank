package com.example.chen.gank.data.source.remote;

import android.util.Log;

import com.example.chen.gank.data.api.Api;
import com.example.chen.gank.data.api.RetrofitClient;
import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.bean.SearchBean;
import com.example.chen.gank.data.source.GankDailySource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 20:13
 */
public class GankDailyRemoteSource implements GankDailySource {
    private static GankDailyRemoteSource sGankDailyRemoteSource;

    private Api mApi;

    private GankDailyRemoteSource(Api api) {
        mApi = api;
    }

    public static GankDailyRemoteSource getInstance() {
        if (sGankDailyRemoteSource == null) {
            synchronized (GankDailyRemoteSource.class) {
                if (sGankDailyRemoteSource == null) {
                    sGankDailyRemoteSource = new GankDailyRemoteSource(RetrofitClient.createService());
                }
            }
        }
        return sGankDailyRemoteSource;
    }

    @Override
    public MutableLiveData<GankDailyResult> getGankDailyResults() {
        MutableLiveData<GankDailyResult> liveData = new MutableLiveData<>();
        mApi.getDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankDailyResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GankDailyResult gankDailyResult) {
                        liveData.setValue(gankDailyResult);
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

    @Override
    public MutableLiveData<Day> getDayHistory() {
        MutableLiveData<Day> liveData = new MutableLiveData<>();
        mApi.getDayHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Day>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Day day) {
                        liveData.setValue(day);
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

    @Override
    public MutableLiveData<GankDailyResult> getDay(String year, String month, String day) {
        MutableLiveData<GankDailyResult> liveData = new MutableLiveData<>();
        mApi.getDay(year, month, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankDailyResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GankDailyResult gankDailyResult) {
                        liveData.setValue(gankDailyResult);
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

    @Override
    public void collectGank(Gank gank) {

    }

    @Override
    public void cancelCollect(Gank gank) {

    }

    @Override
    public LiveData<Gank> isCollected(Gank gank) {
        return null;
    }

    @Override
    public MutableLiveData<List<Gank>> getGanks() {
        return null;
    }

    @Override
    public MutableLiveData<SearchBean> search(String keywords, String category, int count, int page) {
        MutableLiveData<SearchBean> liveData = new MutableLiveData<>();
        mApi.search(keywords, category, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        liveData.setValue(searchBean);
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
