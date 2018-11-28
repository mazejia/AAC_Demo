package com.example.mazejia.aac.aac_demo.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.mazejia.aac.aac_demo.bean.Girl;

/**
 * Created by mazejia on 2018/11/28.
 */

@Database(entities = {Girl.class},version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract GirlDao girlDao();
}
