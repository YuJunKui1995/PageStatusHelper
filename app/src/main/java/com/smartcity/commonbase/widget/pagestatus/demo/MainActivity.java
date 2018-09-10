package com.smartcity.commonbase.widget.pagestatus.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smartcity.commonbase.widget.pagestatus.Builder;
import com.smartcity.commonbase.widget.pagestatus.OnErrorClickListener;
import com.smartcity.commonbase.widget.pagestatus.PageStatusHelper;
import com.smartcity.commonbase.widget.pagestatus.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private PageStatusHelper helper;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Builder builder = new Builder(this);
//        builder.setTextColor(Color.BLACK);
//        builder.setErrorText("出错误啦");
//        builder.setEmptyImage(R.drawable.ic_launcher_background);
//        builder.setLoadingLayout(R.layout.activity_main);

        TextView textView = new TextView(this);
        textView.setText("我是new出来的textview");
        textView.setTextColor(Color.RED);
        builder.setLoadingView(textView);

        PageStatusHelper helper = new PageStatusHelper(this, builder);

        helper.bindView(findViewById(R.id.content))
                .refreshPageStatus(PageStatusHelper.LOADING)
                .setOnErrorClickListener(new OnErrorClickListener() {
                    @Override
                    public void onErrorClick(View v) {
                        loadData();
                    }
                });

        loadData();

    }


    private void loadData() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                helper.refreshPageStatus(PageStatusHelper.CONTENT);
                //根据逻辑调用
//                helper.refreshPageStatus(PageStatusHelper.ERROR);
//                helper.refreshPageStatus(PageStatusHelper.EMPTY);
//                helper.refreshPageStatus(PageStatusHelper.NO_LOGIN);
//                helper.refreshPageStatus(PageStatusHelper.NET_WORK);

            }
        }, 2000);

    }

}
