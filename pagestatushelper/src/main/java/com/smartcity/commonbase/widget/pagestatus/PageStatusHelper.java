package com.smartcity.commonbase.widget.pagestatus;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: YuJunKui
 * Time:2017/12/8 9:24
 * Tips:  页面状态类
 */

public class PageStatusHelper {

    private View bindView;
    private Builder builder;

    private ViewStatusController controller;
    private OnErrorClickListener onErrorClickListener;
    private OnNoLoginClickListener onNoLoginClickListener;
    private OnEmptyClickListener onEmptyClickListener;

    private LayoutParams params;
    public static final byte ERROR = 0, NO_LOGIN = 1, LOADING = 2, NET_WORK = 3, EMPTY = 4, CONTENT = 5;
    private View lastView;

    private static final String TAG = "PageStatusHelper";

    @PageStatusValue
    private int currentPageStatusValue = CONTENT;

    private String bindViewBgColor;

    /**
     * @param context 用于构建view
     */
    public PageStatusHelper(Context context) {
        this(context, new Builder(context));

    }

    /**
     * @param context 用于构建view
     */
    public PageStatusHelper(Context context, Builder builder) {
        setBuilder(builder);
        controller = new ViewStatusController();
    }

    public PageStatusHelper setLayoutParams(LayoutParams params) {
        this.params = params;
        return this;
    }


    public PageStatusHelper bindView(View bindView) {

        if (this.bindView != null) {
            return this;// song 没抛运行时异常
        }

        this.bindView = bindView;
        this.bindViewBgColor = ColorUtils.obtainBgColor(bindView);

        return this;
    }

    @Deprecated
    public void bindActivity(Activity bindActivity) {
        this.bindView = bindActivity.getWindow().getDecorView();
    }

    public PageStatusHelper setBuilder(Builder builder) {
        this.builder = builder;
        return this;
    }

    public Builder getBuilder() {
        return builder;
    }

    /**
     * 此方法会将在GONE状态下的bindView改为INVISIBLE
     *
     * @param pageStatusValue
     */
    public PageStatusHelper refreshPageStatus(@PageStatusValue final int pageStatusValue) {

        //状态一样不做处理
        if (currentPageStatusValue == pageStatusValue) {
            return this;
        }

        currentPageStatusValue = pageStatusValue;

        if (bindView.getVisibility() == View.GONE) {
            bindView.setVisibility(View.INVISIBLE);
        }

        //没有绘制完成的
        if (bindView.getWidth() == 0) {

            bindView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    refreshStatus(pageStatusValue);
                    bindView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

        } else {
            refreshStatus(pageStatusValue);
        }

        return this;
    }

    private void refreshStatus(@PageStatusValue final int pageStatusValue) {
        if (lastView != null) {
            lastView.setVisibility(View.GONE);
        }

        builder.setBindViewBgColor(bindViewBgColor);

        if (pageStatusValue == CONTENT) {

            controller.showContent();
            return;
        }

        if (pageStatusValue == ERROR) {

            lastView = builder.buildErrorView(onErrorClickListener != null);
        } else if (pageStatusValue == EMPTY) {

            lastView = builder.buildEmptyView();
        } else if (pageStatusValue == LOADING) {

            lastView = builder.buildLoadingView();
        } else if (pageStatusValue == NET_WORK) {

            lastView = builder.buildNetWorkView(onErrorClickListener != null);
        } else if (pageStatusValue == NO_LOGIN) {

            lastView = builder.buildNoLoginView();
        } else {
            return;
        }

        //绑定事件
        lastView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((pageStatusValue == ERROR || pageStatusValue == NET_WORK) && onErrorClickListener != null) {


                    //改变状态
                    refreshPageStatus(LOADING);
                    onErrorClickListener.onErrorClick(v);
                } else if (pageStatusValue == NO_LOGIN && onNoLoginClickListener != null) {
                    onNoLoginClickListener.OnNoLoginClick(v);
                }else if(pageStatusValue == EMPTY && onEmptyClickListener != null){
                    onEmptyClickListener.onEmptyClick(v);
                }

            }
        });

        controller.showViewStatus(bindView, lastView, params);
    }


    public PageStatusHelper setOnNoLoginClickListener(OnNoLoginClickListener onNoLoginClickListener) {
        this.onNoLoginClickListener = onNoLoginClickListener;
        return this;
    }

    @Deprecated
    public void showContent() {
        refreshPageStatus(CONTENT);
    }

    @IntDef({ERROR, NO_LOGIN, LOADING, NET_WORK, EMPTY, CONTENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PageStatusValue {
    }

    public void setOnErrorClickListener(OnErrorClickListener onErrorClickListener) {
        this.onErrorClickListener = onErrorClickListener;
    }

    public void setOnEmptyClickListener(OnEmptyClickListener onEmptyClickListener){
        this.onEmptyClickListener = onEmptyClickListener;
    }

}
