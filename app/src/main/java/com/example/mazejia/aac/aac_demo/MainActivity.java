package com.example.mazejia.aac.aac_demo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mazejia.aac.aac_demo.adapter.ListAdapter;
import com.example.mazejia.aac.aac_demo.bean.Girl;
import com.example.mazejia.aac.aac_demo.data.DataRepository;
import com.example.mazejia.aac.aac_demo.data.LocalDataRepository;
import com.example.mazejia.aac.aac_demo.data.RemoteDataRepository;
import com.example.mazejia.aac.aac_demo.view.LifeCycleView;
import com.example.mazejia.aac.aac_demo.viewmodel.ListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private View rootView;
    private ListViewModel listViewModel;
    private LifeCycleView lifeCycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData(){
        ListViewModel.Factory factory = new ListViewModel.Factory(MyApplication.getInstance(),
                DataRepository.getInstance(RemoteDataRepository.getInstance(), LocalDataRepository.getInstance()));
        listViewModel = ViewModelProviders.of(this,factory).get(ListViewModel.class);

        listViewModel.getListData().observe(this, new Observer<List<Girl>>() {
            @Override
            public void onChanged(@Nullable List<Girl> girls) {
                if(girls == null || girls.size() == 0){
                    return;
                }
                listAdapter.setGirlList(girls);
            }
        });

        listViewModel.getLoadState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean == null){
                    return;
                }
                if(swipeRefreshLayout.isRefreshing()){
                    if(!aBoolean){
                        swipeRefreshLayout.setRefreshing(false);
                    }
                } else {
                    progressBar.setVisibility(aBoolean?View.VISIBLE:View.GONE);
                }
            }
        });

        swipeRefreshLayout.setRefreshing(true);
        listViewModel.loadFirstPage();

        lifeCycleView = new LifeCycleView();
        getLifecycle().addObserver(lifeCycleView);
    }

    private void initView(){
        progressBar = findViewById(R.id.load_more_bar);
        recyclerView = findViewById(R.id.rv_test_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                int lastPosition = layoutManager
                        .findLastVisibleItemPosition();
                if (lastPosition == listAdapter.getItemCount() - 1) {
                    // 上拉加载更多数据
                    listViewModel.loadNextPageGirls();
                }
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                listAdapter.clearGirlList();
                listViewModel.loadFirstPage();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        rootView = findViewById(R.id.cl_parent);

    }
}
