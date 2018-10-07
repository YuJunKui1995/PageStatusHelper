package com.smartcity.commonbase.widget.pagestatus.test;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;

import com.smartcity.commonbase.widget.pagestatus.ViewUtils;

/**
 * Author: YuJunKui
 * Time:2018/10/7 16:25
 * Tips:
 */
public class LoadingGifView extends AppCompatImageView {

    public LoadingGifView(Context context) {
        this(context, null);
    }

    public LoadingGifView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingGifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResource(R.drawable.ic_loading);
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "rotation", 0, 360);
        animator.setDuration(1000);
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = ViewUtils.dip2px(getContext(), 80);
        setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), width + getPaddingTop() + getPaddingBottom());
    }
}
