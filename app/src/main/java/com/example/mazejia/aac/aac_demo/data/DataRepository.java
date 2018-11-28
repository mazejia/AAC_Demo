package com.example.mazejia.aac.aac_demo.data;

import android.arch.lifecycle.LiveData;

import com.example.mazejia.aac.aac_demo.MyApplication;
import com.example.mazejia.aac.aac_demo.bean.Girl;
import com.example.mazejia.aac.aac_demo.utils.Util;

import java.util.List;

/**
 * Created by mazejia on 2018/11/27.
 */

public class DataRepository {

    private static DataRepository sInstance;
    private DataSource remoteDataSource;
    private DataSource localDataSource;

    private DataRepository(DataSource remoteDataSource,DataSource localDataSource){
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static DataRepository getInstance(DataSource remoteDataSource,DataSource localDataSource){
        if(sInstance == null){
            synchronized (DataRepository.class){
                if(sInstance == null){
                    sInstance = new DataRepository(remoteDataSource,localDataSource);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Girl>> getGirlList(Integer input){
        if(Util.isNetworkConnected(MyApplication.getInstance())){
            return remoteDataSource.getGirlList(input);
        } else {
            return localDataSource.getGirlList(input);
        }
    }

    public LiveData<Boolean> isLoadingState(){
        if(Util.isNetworkConnected(MyApplication.getInstance())){
            return remoteDataSource.isLoadingGirlList();
        } else {
            return remoteDataSource.isLoadingGirlList();
        }
    }
}
