package com.example.chen.gank.data.source.local;

import android.widget.ListView;

import com.example.chen.gank.data.bean.Gank;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 21:01
 */
@Dao
public interface GankDao {

    /**
     * 获取所有收藏 Gank
     *
     * @return
     */
    @Query("SELECT * FROM Ganks")
    LiveData<List<Gank>> getGanks();

    /**
     * 添加收藏 Gank
     *
     * @param gank
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addGank(Gank gank);

    /**
     * 删除收藏 Gank
     *
     * @param gank
     */
    @Delete
    void deleteGank(Gank gank);

    /**
     * 查询 Gank 是否存在
     *
     * @param id
     * @return
     */
    @Query("SELECT * FROM Ganks Where id = :id")
    LiveData<Gank> existGank(String id);
}
