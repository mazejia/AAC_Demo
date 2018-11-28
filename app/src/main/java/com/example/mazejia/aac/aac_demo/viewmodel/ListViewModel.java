package com.example.mazejia.aac.aac_demo.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.mazejia.aac.aac_demo.bean.Girl;
import com.example.mazejia.aac.aac_demo.data.DataRepository;

import java.util.List;

/**
 * Created by mazejia on 2018/11/27.
 */

public class ListViewModel extends AndroidViewModel {

    private final MutableLiveData<Integer> mListIndex = new MutableLiveData<Integer>();

    private final LiveData<List<Girl>> mList;

    private DataRepository dataRepository;

    private ListViewModel(@NonNull Application application,DataRepository repository) {
        super(application);
        this.dataRepository = repository;
        mList = Transformations.switchMap(mListIndex, new Function<Integer, LiveData<List<Girl>>>() {
            @Override
            public LiveData<List<Girl>> apply(Integer input) {
                return dataRepository.getGirlList(input);
            }
        });
    }

    public LiveData<List<Girl>> getListData() {
        return mList;
    }

    public LiveData<Boolean> getLoadState(){
        return dataRepository.isLoadingState();
    }

    public void loadFirstPage(){
        mListIndex.setValue(1);
    }

    public void loadNextPageGirls(){
        if(mListIndex.getValue() == null){
            mListIndex.setValue(1);
        } else {
            mListIndex.setValue(mListIndex.getValue() + 1);
        }
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        private final Application application;

        private final DataRepository dataRepository;

        public Factory(Application instance,DataRepository repository){
            this.application = instance;
            this.dataRepository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ListViewModel(application,dataRepository);
        }
    }
}
