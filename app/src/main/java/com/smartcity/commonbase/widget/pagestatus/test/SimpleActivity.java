package com.smartcity.commonbase.widget.pagestatus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.smartcity.commonbase.widget.pagestatus.PageStatusHelper;

/**
 * Author: YuJunKui
 * Time:2018/9/11 17:31
 * Tips:
 */
public class SimpleActivity extends AppCompatActivity {

    private PageStatusHelper statusHelper;

    //可以是list 也可以是任何view
    private View contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("简单使用");
        setContentView(R.layout.activity_simple);

        contentView = findViewById(R.id.rv_content);

        statusHelper = new PageStatusHelper(this);
        statusHelper.bindView(contentView);

        statusHelper.setOnErrorClickListener(v -> {
            // 重新开始你的请求
        });

        statusHelper.setOnNoLoginClickListener(v -> {
            //打开登录页面
            //建议写个静态方法 构造一系列通用视图 以及 打开登录页面
        });


        statusHelper.refreshPageStatus(PageStatusHelper.LOADING);
        //模拟接口速度很快，LoadingView还没有绘制完成就调用了Content的情况
        statusHelper.refreshPageStatus(PageStatusHelper.CONTENT);

    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_content:
                //数据加载完成调用这个方法
                statusHelper.refreshPageStatus(PageStatusHelper.CONTENT);
                break;

            case R.id.btn_empty:
                //空视图
                statusHelper.refreshPageStatus(PageStatusHelper.EMPTY);
                break;

            case R.id.btn_loading:
                //加载中
                statusHelper.refreshPageStatus(PageStatusHelper.LOADING);
                break;

            case R.id.btn_loading_error:
                //加载失败
                statusHelper.refreshPageStatus(PageStatusHelper.ERROR);
                break;

            case R.id.btn_no_login:
                //未登录
                statusHelper.refreshPageStatus(PageStatusHelper.NO_LOGIN);
                break;

            case R.id.btn_no_network:
                //没有网络
                statusHelper.refreshPageStatus(PageStatusHelper.NET_WORK);
                break;
        }
    }

}
