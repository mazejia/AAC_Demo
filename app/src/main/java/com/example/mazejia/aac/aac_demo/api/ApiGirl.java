package com.example.mazejia.aac.aac_demo.api;

import com.example.mazejia.aac.aac_demo.bean.Girl;
import com.example.mazejia.aac.aac_demo.bean.GirlData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mazejia on 2018/11/27.
 */

public interface ApiGirl {

    @GET("api/data/福利/10/{index}")
    Call<GirlData> getGirlsData(@Path("index") int index);
}
