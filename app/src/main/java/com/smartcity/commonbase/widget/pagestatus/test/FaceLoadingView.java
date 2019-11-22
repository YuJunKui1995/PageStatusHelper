package com.smartcity.commonbase.widget.pagestatus.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Author: YuJunKui
 * Time:2018/5/26 11:42
 * Tips:
 */
public class FaceLoadingView extends AppCompatImageView {

    public FaceLoadingView(Context context) {
        this(context, null);
    }

    public FaceLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FaceLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);
        setImageResource(R.drawable.game_anim_loading);
        ((AnimationDrawable) this.getDrawable()).start();
    }


}
