package com.smartcity.commonbase.widget.pagestatus.imp;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;

import com.smartcity.commonbase.widget.ViewUtils;
import com.smartcity.commonbase.widget.pagestatus.LayoutParams;

/**
 * Author: YuJunKui
 * Time:2017/12/8 10:57
 * Tips:
 */

public class ViewGroupViewStatusImp implements ViewStatusInterface {

    @Override
    public void addStatusView(View bindView, View addView, LayoutParams params) {

        ViewGroup parent = (ViewGroup) bindView.getParent();

        if (parent.indexOfChild(addView) == -1) {

            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            if (params.centerInParent == true) {

                //获取addview宽高
                int[] addViewMetrics = ViewUtils.getViewMetrics(addView);
                int width = addViewMetrics[0];
                int height = addViewMetrics[1];

                //获取bindview宽高
                int[] bindViewMetrics = ViewUtils.getViewMetrics(bindView);
                int parentWidth = bindViewMetrics[0];
                int parentHeight = bindViewMetrics[1];


                int left = (parentWidth - width) / 2;
                int top = (parentHeight - height) / 2;
                addView.setPadding(left, top, left, top);

            } else {

                addView.setPadding(params.paddingLeft, params.paddingTop, params.paddingRight, params.paddingBottom);
            }

            parent.addView(addView, layoutParams);

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
