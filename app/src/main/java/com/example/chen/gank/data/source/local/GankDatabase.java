package com.example.chen.gank.data.source.local;

import com.example.chen.gank.app.GankApp;
import com.example.chen.gank.data.bean.Gank;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/14 20:59
 */
@Database(entities = {Gank.class}, version = 1, exportSchema = false)
public abstract class GankDatabase extends RoomDatabase {
    private static GankDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract GankDao ganksDao();

    public static GankDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (sLock) {
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
