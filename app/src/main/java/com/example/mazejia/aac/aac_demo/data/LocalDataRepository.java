package com.example.mazejia.aac.aac_demo.data;

import android.arch.lifecycle.LiveData;

import com.example.mazejia.aac.aac_demo.bean.Girl;
import com.example.mazejia.aac.aac_demo.data.db.AppDataBaseManager;

import java.util.List;

/**
 * Created by mazejia on 2018/11/27.
 */

public class LocalDataRepository implements DataSource {

    private static LocalDataRepository sInstance;

    public static LocalDataRepository getInstance(){
        if(sInstance == null){
            synchronized (LocalDataRepository.class){
                if(sInstance == null){
                    sInstance = new LocalDataRepository();
                }
            }
        }
        return sInstance;
    }

    @Override
    public LiveData<List<Girl>> getGirlList(int index) {
        return AppDataBaseManager.getInstance().loadGirlList();
    }

    @Override
    public LiveData<Boolean> isLoadingGirlList() {
        return AppDataBaseManager.getInstance().isLoadingState();
    }
}
