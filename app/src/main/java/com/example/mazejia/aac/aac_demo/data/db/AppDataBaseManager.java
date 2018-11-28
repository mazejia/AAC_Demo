package com.example.mazejia.aac.aac_demo.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.mazejia.aac.aac_demo.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazejia on 2018/11/28.
 */

public class AppDataBaseManager {

    private static final String DATABASE_NAME = "architecture-db";

    private final MutableLiveData<Boolean> mIsLoadingList;

    private final MutableLiveData<List<Girl>> mGirlsList;

    private static AppDataBaseManager sInstance;

    private AppDataBase mAppDataBase;

    {
        mIsLoadingList = new MutableLiveData<>();
        mGirlsList = new MutableLiveData<>();
    }

    private AppDataBaseManager(){

    }

    public static AppDataBaseManager getInstance(){
        if(sInstance == null){
            synchronized (AppDataBaseManager.class){
                if(sInstance == null){
                    sInstance = new AppDataBaseManager();
                }
            }
        }
        return sInstance;
    }

    public void createDB(Context context) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                Context context = params[0].getApplicationContext();
                mAppDataBase = Room.databaseBuilder(context,AppDataBase.class,DATABASE_NAME).build();
                return null;
            }
        }.execute(context.getApplicationContext());
    }


    public void insertGirlList(final List<Girl> girls) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                mAppDataBase.beginTransaction();
                try {
                    mAppDataBase.girlDao().insertGirls(girls);
                    mAppDataBase.setTransactionSuccessful();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    mAppDataBase.endTransaction();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<Girl>> loadGirlList(){
        mIsLoadingList.setValue(true);
        new AsyncTask<Void,Void,List<Girl>>(){

            @Override
            protected List<Girl> doInBackground(Void... voids) {
                List<Girl> result = new ArrayList<>();
                mAppDataBase.beginTransaction();
                try{
                    result.addAll(mAppDataBase.girlDao().loadAllGirls());
                    mAppDataBase.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    mAppDataBase.endTransaction();
                }
                return result;
            }

            @Override
            protected void onPostExecute(List<Girl> girls) {
                super.onPostExecute(girls);
                mIsLoadingList.setValue(false);
                mGirlsList.setValue(girls);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                mIsLoadingList.setValue(false);
            }

        }.execute();
        return mGirlsList;
    }

    public LiveData<Boolean> isLoadingState(){
        return mIsLoadingList;
    }

}
