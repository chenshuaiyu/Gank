package com.example.chen.gank.data.source.local;

import com.example.chen.gank.app.GankApp;
import com.example.chen.gank.data.bean.Gank;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 20:59
 */
@Database(entities = {Gank.class}, version = 1, exportSchema = false)
public abstract class GankDatabase extends RoomDatabase {
    private static volatile GankDatabase INSTANCE;

    /**
     * 获取 Gank DAO
     *
     * @return
     */
    public abstract GankDao ganksDao();

    public static GankDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (GankDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(GankApp.getApp(), GankDatabase.class, "Ganks.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
