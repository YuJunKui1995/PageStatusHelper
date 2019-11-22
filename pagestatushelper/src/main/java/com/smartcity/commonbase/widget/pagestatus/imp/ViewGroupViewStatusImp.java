package com.smartcity.commonbase.widget.pagestatus.imp;

import android.util.Log;
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

public abstract class ViewGroupViewStatusImp implements ViewStatusInterface {

    private static final String TAG = "ViewGroupViewStatusImp";

    protected View addView;
    protected View bindView;

    protected View lastAddView;


    //线性布局插入view后会对bindView的的宽高会变化，导致的问题
    //比如当前在加载中，在线性布局下  ？？？？
    private int bindViewWidth = -1, bindViewHeight = -1;

    @Override
    public void addStatusView(final View bindView, final View addView, LayoutParams params) {

        this.addView = addView;
        this.bindView = bindView;

        ViewGroup parent = (ViewGroup) bindView.getParent();

        if (addView.getTag() == null) {

            final ViewGroup.MarginLayoutParams binViewParams = (ViewGroup.MarginLayoutParams) bindView.getLayoutParams();

            if (params.centerInParent == true) {


                addView.post(new Runnable() {
                    @Override
                    public void run() {

                        if (lastAddView != null) {
                            lastAddView.setVisibility(View.INVISIBLE);
                        }
                        lastAddView = addView;

                        Log.i(TAG, "call addStatusView run addView=" + addView);
                        //获取bindview宽高
                        if (bindViewWidth == -1) {
                            int[] bindViewMetrics = ViewUtils.getBindViewMetrics(bindView);
                            bindViewWidth = bindViewMetrics[0];
                            bindViewHeight = bindViewMetrics[1];
                        }

                        int width = addView.getWidth();
                        int height = addView.getHeight();

                        int left = (bindViewWidth - width) / 2;
                        int top = (bindViewHeight - height) / 2;

                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) addView.getLayoutParams();
                        layoutParams.setMargins(left, top, 0, 0);

                        initLayoutParams(layoutParams);

                        addView.setTag(ADD_View_TAG);
                        addView.setLayoutParams(addView.getLayoutParams());
                        addView.setVisibility(View.VISIBLE);
                    }
                });

            } else {

                addView.setPadding(params.paddingLeft, params.paddingTop, params.paddingRight, params.paddingBottom);
            }

            if (addView.getId() == View.NO_ID) {
                addView.setId(View.generateViewId());
            }
            parent.addView(addView);
            hideContent(bindView);
        } else {
            if (lastAddView != null) {
                lastAddView.setVisibility(View.INVISIBLE);
            }
            lastAddView=addView;
            addView.setVisibility(View.VISIBLE);
            hideContent(bindView);
        }
    }

    /**
     * 注意 init 你不需要在这个方法中调用setLayoutParams
     *
     * @param layoutParams
     */
    protected abstract void initLayoutParams(ViewGroup.LayoutParams layoutParams);

    /**
     * 隐藏content view 默认INVISIBLE隐藏
     *
     * @param bindView
     */
    public void hideContent(View bindView) {
        bindView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showContentView() {

        bindView.setVisibility(View.VISIBLE);
        addView.setVisibility(View.GONE);
    }
}
