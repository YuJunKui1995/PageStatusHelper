package com.smartcity.commonbase.widget.pagestatus.imp;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.view.ViewGroup;

import com.smartcity.commonbase.widget.pagestatus.LayoutParams;
import com.smartcity.commonbase.widget.pagestatus.ViewUtils;

/**
 * Author: YuJunKui
 * Time:2018/5/14 15:21
 * Tips:
 */
public class ConstraintLayoutViewStatusImp extends ViewGroupViewStatusImp {


    @Override
    public void addStatusView(View bindView, View addView, LayoutParams params) {

        //((ConstraintLayout) bindView.getParent()).addView(addView, bindView.getLayoutParams());
        //这个是不行的  ConstraintLayout并没有处理 addView

        super.addStatusView(bindView, addView, params);
    }


    @Override
    protected void initLayoutParams(ViewGroup.LayoutParams addLayoutParams) {

        ((ViewGroup.MarginLayoutParams) addLayoutParams).setMargins(0, 0, 0, 0);
        int rulesCount = 0;

        ConstraintLayout layout = (ConstraintLayout) bindView.getParent();
        //因为约束布局使用ConstraintSet新增view的时候 必须要其他view要有id
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v=layout.getChildAt(i);
            if (v.getId() == View.NO_ID) {
                v.setId(View.generateViewId());
            }
        }

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) bindView.getLayoutParams();

        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        //获取对应关系和间隔
        //设置对应关系

        //左右只是设置了Margin但是没有设置以什么为标准
        //比如设置了leftMargin和rightMargin  没有设置leftToRight或者leftToLeft   rightToRight或者rightToLeft
        //目前只支持leftMargin==rightToRight的显示
        //这里的方案还要更换
        if (layoutParams.leftMargin == layoutParams.rightMargin &&
                layoutParams.leftToLeft == -1 && layoutParams.leftToRight == -1 &&
                layoutParams.rightToRight == -1 && layoutParams.rightToLeft == -1
        ) {
            layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.leftMargin = 0;
            layoutParams.rightToRight = 0;
        }
        if (layoutParams.topMargin == layoutParams.bottomMargin &&
                layoutParams.topToTop == -1 && layoutParams.topToBottom == -1 &&
                layoutParams.bottomToBottom == -1 && layoutParams.bottomToTop == -1
        ) {
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        }

        //left to left  start to start
        if (layoutParams.leftToLeft != -1 || layoutParams.startToStart != -1) {
            int leftToLeft = layoutParams.leftToLeft != -1 ? layoutParams.leftToLeft : layoutParams.startToStart;
            set.connect(addView.getId(), ConstraintSet.START, leftToLeft, ConstraintSet.START, layoutParams.leftMargin);
            rulesCount++;
        }

        //right to right  end to end
        if (layoutParams.rightToRight != -1 || layoutParams.endToEnd != -1) {
            int rightToRight = layoutParams.rightToRight != -1 ? layoutParams.rightToRight : layoutParams.endToEnd;
            set.connect(addView.getId(), ConstraintSet.END, rightToRight, ConstraintSet.END, layoutParams.rightMargin);
            rulesCount++;
        }

        //top to top
        if (layoutParams.topToTop != -1) {
            set.connect(addView.getId(), ConstraintSet.TOP, layoutParams.topToTop, ConstraintSet.TOP, layoutParams.topMargin);
            rulesCount++;
        }

        //bottom to bottom
        if (layoutParams.bottomToBottom != -1) {
            set.connect(addView.getId(), ConstraintSet.BOTTOM, layoutParams.bottomToBottom, ConstraintSet.BOTTOM, layoutParams.bottomMargin);
            rulesCount++;
        }

        //left to right  start to end
        if (layoutParams.leftToRight != -1 || layoutParams.startToEnd != -1) {
            int leftToRight = layoutParams.rightToRight != -1 ? layoutParams.rightToRight : layoutParams.startToEnd;
            set.connect(addView.getId(), ConstraintSet.LEFT, leftToRight, ConstraintSet.RIGHT, layoutParams.leftMargin);
            rulesCount++;
        }

        //right to left  end to start
        if (layoutParams.rightToLeft != -1 && layoutParams.endToStart != -1) {
            int rightToLeft = layoutParams.rightToLeft != -1 ? layoutParams.rightToLeft : layoutParams.endToStart;
            set.connect(addView.getId(), ConstraintSet.RIGHT, rightToLeft, ConstraintSet.LEFT, layoutParams.rightMargin);
            rulesCount++;
        }

        //top to bottom
        if (layoutParams.topToBottom != -1) {
            set.connect(addView.getId(), ConstraintSet.TOP, layoutParams.topToBottom, ConstraintSet.BOTTOM, layoutParams.topMargin);
            rulesCount++;
        }

        //bottom to top
        if (layoutParams.bottomToTop != -1) {
            set.connect(addView.getId(), ConstraintSet.BOTTOM, layoutParams.bottomToTop, ConstraintSet.TOP, layoutParams.bottomMargin);
            rulesCount++;
        }

        //检查约束布局的参数  理论应该是4个
        if (rulesCount < 3) {
            set.connect(addView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            set.connect(addView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
            set.connect(addView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
            set.connect(addView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        }

        if (addLayoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT
                && addLayoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT
                && rulesCount >= 4
        ) {
            set.constrainWidth(addView.getId(), 0);
            set.constrainHeight(addView.getId(), 0);
        }

        set.applyTo(layout);
    }
}
