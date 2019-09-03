package com.smartcity.commonbase.widget.pagestatus.imp;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.smartcity.commonbase.widget.pagestatus.LayoutParams;
import com.smartcity.commonbase.widget.pagestatus.ViewUtils;

/**
 * Author: YuJunKui
 * Time:2017/12/8 10:57
 * Tips:
 */

public class ViewGroupViewStatusImp implements ViewStatusInterface {

    @Override
    public void addStatusView(final View bindView, final View addView, LayoutParams params) {

        ViewGroup parent = (ViewGroup) bindView.getParent();

        if (parent.indexOfChild(addView) == -1) {

            if (params.centerInParent == true) {

                addView.post(new Runnable() {
                    @Override
                    public void run() {

                         int width = addView.getWidth();
                        int height = addView.getHeight();

                        //获取bindview宽高
                        int[] bindViewMetrics = ViewUtils.getBindViewMetrics(bindView);
                        int parentWidth = bindViewMetrics[0];
                        int parentHeight = bindViewMetrics[1];

                        int left = (parentWidth - width) / 2;
                        int top = (parentHeight - height) / 2;

                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) addView.getLayoutParams();
                        layoutParams.setMargins(left, top, 0, 0);

                        addView.setLayoutParams(layoutParams);
                    }
                });

            } else {

                addView.setPadding(params.paddingLeft, params.paddingTop, params.paddingRight, params.paddingBottom);
            }

            parent.addView(addView);

        }
        bindView.setVisibility(View.INVISIBLE);
        addView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentView(View bindView, View addView) {

        bindView.setVisibility(View.VISIBLE);
        addView.setVisibility(View.GONE);
    }
}
