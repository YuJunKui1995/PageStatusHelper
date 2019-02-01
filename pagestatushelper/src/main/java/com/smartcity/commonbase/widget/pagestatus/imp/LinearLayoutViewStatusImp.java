package com.smartcity.commonbase.widget.pagestatus.imp;

import android.view.View;
import android.view.ViewGroup;

import com.smartcity.commonbase.widget.pagestatus.LayoutParams;
import com.smartcity.commonbase.widget.pagestatus.ViewUtils;

/**
 * Author: YuJunKui
 * Time:2017/12/8 10:57
 * Tips:
 */

public class LinearLayoutViewStatusImp  extends ViewGroupViewStatusImp {

    @Override
    public void addStatusView(View bindView, View addView, LayoutParams params) {
        super.addStatusView(bindView,addView,params);
        bindView.setVisibility(View.GONE);
        addView.setVisibility(View.VISIBLE);
    }

}
