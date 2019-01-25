package com.example.mazejia.aac.aac_demo.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;


/**
 * 自管理生命周期view
 * lifeCycle view
 * Created by mazejia on 2019/1/25.
 */

public class LifeCycleView implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate(){
        //TODO
        Log.e("LifeCycleView","onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(){
        //TODO
        Log.e("LifeCycleView","onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onAny(){
        //TODO
        Log.e("LifeCycleView","onAny");
    }
}
