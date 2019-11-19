package com.smartcity.commonbase.widget.pagestatus.imp;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Author: YuJunKui
 * Time:2017/12/8 10:57
 * Tips:
 */

public class RelativeLayoutViewStatusImp extends ViewGroupViewStatusImp {

    @Override
    protected void initLayoutParams(ViewGroup.LayoutParams layoutParams) {

        //状态view的params
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutParams;
        //bindView的
        RelativeLayout.LayoutParams bindViewParams = (RelativeLayout.LayoutParams) bindView.getLayoutParams();

        //bindView的对齐关系
        int[] rules = bindViewParams.getRules();
        for (int i = 0; i < rules.length; i++) {
            params.addRule(i, rules[i]);
        }
        // ok

    }

}
