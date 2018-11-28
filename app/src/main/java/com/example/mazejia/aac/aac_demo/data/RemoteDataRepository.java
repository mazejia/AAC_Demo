package com.example.mazejia.aac.aac_demo.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.mazejia.aac.aac_demo.api.ApiGirl;
import com.example.mazejia.aac.aac_demo.api.ApiManager;
import com.example.mazejia.aac.aac_demo.bean.Girl;
import com.example.mazejia.aac.aac_demo.bean.GirlData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mazejia on 2018/11/27.
 */

public class RemoteDataRepository implements DataSource {

    private static RemoteDataRepository sIntance;

    private final MutableLiveData<Boolean> mIsLoadingGirlList = new MutableLiveData<>();

    private final MutableLiveData<List<Girl>> mGirlList = new MutableLiveData<>();

    private final ApiGirl mApiGirl;

    private RemoteDataRepository(){
        mApiGirl = ApiManager.getInstance().getApiGirl();
    }

    public static RemoteDataRepository getInstance(){
        if(sIntance == null){
            synchronized (RemoteDataRepository.class){
                if(sIntance == null){
                    sIntance = new RemoteDataRepository();
                }
            }
        }
        return sIntance;
    }

    @Override
    public LiveData<List<Girl>> getGirlList(int index) {
        mIsLoadingGirlList.setValue(true);
        mApiGirl.getGirlsData(index).enqueue(new Callback<GirlData>() {
            @Override
            public void onResponse(Call<GirlData> call, Response<GirlData> response) {
                mIsLoadingGirlList.setValue(false);
                if(response != null && response.isSuccessful()){
                    mGirlList.setValue(response.body().results);
                }
            }

            @Override
            public void onFailure(Call<GirlData> call, Throwable t) {
                mIsLoadingGirlList.setValue(false);
            }
        });
        return mGirlList;
    }

    @Override
    public LiveData<Boolean> isLoadingGirlList() {
        return mIsLoadingGirlList;
    }
}
