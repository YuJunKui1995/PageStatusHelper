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

public class LinearLayoutViewStatusImp implements ViewStatusInterface {

    private static final String TAG = "LinearLayoutViewStatusI";

    //线性布局插入view后会对bindView的的宽高会变化，导致的问题
    private int bindViewWidth = -1, bindViewHeight = -1;

    @Override
    public void addStatusView(final View bindView, final View addView, LayoutParams params) {

        ViewGroup parent = (ViewGroup) bindView.getParent();

        if (parent.indexOfChild(addView) == -1) {

            if (params.centerInParent) {

                //获取bindview宽高
                if (bindViewWidth == -1) {
                    int[] bindViewMetrics = ViewUtils.getBindViewMetrics(bindView);
                    bindViewWidth = bindViewMetrics[0];
                    bindViewHeight = bindViewMetrics[1];
                }

                addView.post(new Runnable() {
                    @Override
                    public void run() {

                        int width = addView.getWidth();
                        int height = addView.getHeight();

                        int left = (bindViewWidth - width) / 2;
                        int top = (bindViewHeight - height) / 2;

                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) addView.getLayoutParams();
                        layoutParams.setMargins(left, top, 0, 0);

                        addView.setLayoutParams(layoutParams);
                        addView.setVisibility(View.VISIBLE);
                    }
                });

            } else {

                addView.setPadding(params.paddingLeft, params.paddingTop, params.paddingRight, params.paddingBottom);
            }

            Log.i(TAG, "child.getLayoutParams()=" + addView.getLayoutParams());
            parent.addView(addView, parent.indexOfChild(bindView));

            addView.setVisibility(View.INVISIBLE);
        } else {
            bindView.setVisibility(View.GONE);
            addView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showContentView(View bindView, View addView) {

        bindView.setVisibility(View.VISIBLE);
        addView.setVisibility(View.GONE);
    }

}
