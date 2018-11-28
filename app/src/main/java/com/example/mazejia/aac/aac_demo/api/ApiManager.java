package com.example.mazejia.aac.aac_demo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiManager {

    private static final String GIRL_URL = "http://gank.io/";

    private static ApiManager INSTANCE;

    private static ApiGirl sApiGirl;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ApiManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiManager();
                }
            }
        }
        return INSTANCE;
    }

    public ApiGirl getApiGirl() {
        if (sApiGirl == null) {
            synchronized (ApiManager.class) {
                if (sApiGirl == null) {
                    sApiGirl = new Retrofit.Builder()
                            .baseUrl(GIRL_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiGirl.class);
                }
            }
        }
        return sApiGirl;
    }
}
