package com.example.chen.gank.data.bean;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/16 14:44
 */
public class GankDailyBean {
    private String title;
    private List<Gank> mGanks;

    public GankDailyBean(String title, List<Gank> ganks) {
        this.title = title;
        mGanks = ganks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Gank> getGanks() {
        return mGanks;
    }

    public void setGanks(List<Gank> ganks) {
        mGanks = ganks;
    }
}
