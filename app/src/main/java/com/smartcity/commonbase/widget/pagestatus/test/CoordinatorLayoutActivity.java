package com.smartcity.commonbase.widget.pagestatus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.smartcity.commonbase.widget.pagestatus.PageStatusHelper;

/**
 * Author: YuJunKui
 * Time:2019/2/1 17:08
 * Tips:
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("CoordinatorLayout效果");
        setContentView(R.layout.activity_coordinatorlayout);

        View bindView=findViewById(R.id.rv_content);
        PageStatusHelper pageStatusHelper=new PageStatusHelper(this);
        pageStatusHelper.bindView(bindView);

        bindView.postDelayed(() -> pageStatusHelper.refreshPageStatus(PageStatusHelper.LOADING),200);

    }

}
