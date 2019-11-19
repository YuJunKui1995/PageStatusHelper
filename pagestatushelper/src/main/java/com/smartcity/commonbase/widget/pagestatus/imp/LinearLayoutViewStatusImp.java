package com.smartcity.commonbase.widget.pagestatus.imp;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.smartcity.commonbase.widget.pagestatus.LayoutParams;
import com.smartcity.commonbase.widget.pagestatus.ViewUtils;

/**
 * Author: YuJunKui
 * Time:2017/12/8 10:57
 * Tips:  LinearLayoutView和ViewGroupView  存在两点差异
 * 1、LinearLayoutView的bindView不能INVISIBLE
 */

public class LinearLayoutViewStatusImp extends ViewGroupViewStatusImp {

    private static final String TAG = "LinearLayoutViewStatusI";

    @Override
    protected void initLayoutParams(ViewGroup.LayoutParams layoutParams) {
        //nothing  基类的处理已经足够了
    }

    @Override
    public void hideContent(View bindView) {
        bindView.setVisibility(View.GONE);
    }

}
