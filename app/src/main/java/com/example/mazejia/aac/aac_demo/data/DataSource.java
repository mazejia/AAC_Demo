package com.example.mazejia.aac.aac_demo.data;

import android.arch.lifecycle.LiveData;

import com.example.mazejia.aac.aac_demo.bean.Girl;

import java.util.List;


public interface DataSource {

    LiveData<List<Girl>> getGirlList(int index);

    LiveData<Boolean> isLoadingGirlList();
}
