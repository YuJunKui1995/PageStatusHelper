package com.smartcity.commonbase.widget.pagestatus.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smartcity.commonbase.widget.pagestatus.Builder;
import com.smartcity.commonbase.widget.pagestatus.PageStatusHelper;
import com.smartcity.commonbase.widget.pagestatus.R;

public class MainActivity extends AppCompatActivity {

    private PageStatusHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new PageStatusHelper(this, new Builder(this).setTextColor(Color.BLACK).setBgColor(Color.GREEN));
        helper.bindView(findViewById(R.id.content));
        helper.refreshPageStatus(PageStatusHelper.LOADING);
    }
}
