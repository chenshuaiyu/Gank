package com.example.chen.gank.data.source;

import com.example.chen.gank.data.bean.Day;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.data.bean.SearchBean;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 20:07
 */
public interface GankDailySource {

    /**
     * 获取最新干货
     *
     * @return
     */
    MutableLiveData<GankDailyResult> getGankDailyResults();

    /**
     * 获取 Gank 更新日
     *
     * @return
     */
    MutableLiveData<Day> getDayHistory();

    /**
     * 获取某日干货
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    MutableLiveData<GankDailyResult> getDay(String year, String month, String day);

    /**
     * 获取收藏 Gank
     *
     * @return
     */
    LiveData<List<Gank>> getGanks();

    /**
     * 收藏 Gank
     * @param gank
     */
    void collectGank(Gank gank);

    /**
     * 取消收藏 Gank
     *
     * @param gank
     */
    void cancelCollect(Gank gank);

    /**
     * 是否收藏 Gank
     *
     * @param gank
     * @return
     */
    LiveData<Gank> isCollected(Gank gank);

    /**
     * 搜索干货
     *
     * @param keywords
     * @param category
     * @param count
     * @param page
     * @return
     */
    MutableLiveData<SearchBean> search(String keywords, String category, int count, int page);
}
