package com.example.mazejia.aac.aac_demo.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mazejia.aac.aac_demo.bean.Girl;

import java.util.List;

/**
 * Created by mazejia on 2018/11/28.
 */
@Dao
public interface GirlDao {

    @Query("SELECT * FROM girls")
    List<Girl> loadAllGirls();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGirls(List<Girl> girls);
}
