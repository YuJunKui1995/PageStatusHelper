package com.smartcity.commonbase.widget.pagestatus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.smartcity.commonbase.widget.pagestatus.PageStatusHelper;

/**
 * Author: YuJunKui
 * Time:2018/10/7 15:41
 * Tips: 一个页面存在多个接口 需要多个加载状态
 * <p>
 * 这个页面会有一个有意思的玩意
 * 状态的文字会自动设置颜色，是根据取bindView的颜色做颜色反转， 来实现颜色不用主动设置
 * 如果需要主动设置 可以通过Helper.getBuilder().setTextColor()
 */
public class SinglePageMoreStateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("一个页面存在多个接口");
        setContentView(R.layout.activity_single_page_more_state);

        TextView tvList1 = findViewById(R.id.tv_list1);

        PageStatusHelper list1StatusHelper = new PageStatusHelper(this);
        list1StatusHelper.bindView(tvList1);
        //加载中
        list1StatusHelper.refreshPageStatus(PageStatusHelper.LOADING);

        //第二个列表也需要加载状态维护
        TextView tvList2 = findViewById(R.id.tv_list2);

        PageStatusHelper list2StatusHelper = new PageStatusHelper(this);
        list2StatusHelper.bindView(tvList2);
        list2StatusHelper.setOnErrorClickListener(v -> {

        });

        //加载中
        list2StatusHelper.refreshPageStatus(PageStatusHelper.LOADING);


        //模拟第二个列表加载失败
        tvList2.postDelayed(new Runnable() {
            @Override
            public void run() {
                list2StatusHelper.refreshPageStatus(PageStatusHelper.ERROR);
                tvList2.postDelayed(this,2500);
            }
        }, 2500);
    }
}
