package com.smartcity.commonbase.widget.pagestatus.imp;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;

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
//        super.addStatusView(bindView, addView, params);
        //come soon

        //((ConstraintLayout) bindView.getParent()).addView(addView, bindView.getLayoutParams());
        //这个是不行的  ConstraintLayout并没有处理 addView

        ConstraintLayout layout = (ConstraintLayout) bindView.getParent();

        if (layout.indexOfChild(addView) != -1) {
            //已经添加过了
            bindView.setVisibility(View.INVISIBLE);
            addView.setVisibility(View.VISIBLE);
            return;
        }

        int dp10 = ViewUtils.dip2px(bindView.getContext(), 10);
        addView.setPadding(dp10 * 2, dp10, dp10 * 2, dp10);


        addView.setId(View.generateViewId());
        layout.addView(addView);

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
            set.connect(addView.getId(), ConstraintSet.LEFT, leftToLeft, ConstraintSet.LEFT, layoutParams.leftMargin);
        }

        //right to right  end to end
        if (layoutParams.rightToRight != -1 || layoutParams.endToEnd != -1) {
            int rightToRight = layoutParams.rightToRight != -1 ? layoutParams.rightToRight : layoutParams.endToEnd;
            set.connect(addView.getId(), ConstraintSet.RIGHT, rightToRight, ConstraintSet.RIGHT, layoutParams.rightMargin);
        }

        //top to top
        if (layoutParams.topToTop != -1)
            set.connect(addView.getId(), ConstraintSet.TOP, layoutParams.topToTop, ConstraintSet.TOP, layoutParams.topMargin);

        //bottom to bottom
        if (layoutParams.bottomToBottom != -1)
            set.connect(addView.getId(), ConstraintSet.BOTTOM, layoutParams.bottomToBottom, ConstraintSet.BOTTOM, layoutParams.bottomMargin);

        //left to right  start to end
        if (layoutParams.leftToRight != -1 || layoutParams.startToEnd != -1) {
            int leftToRight = layoutParams.rightToRight != -1 ? layoutParams.rightToRight : layoutParams.startToEnd;
            set.connect(addView.getId(), ConstraintSet.LEFT, leftToRight, ConstraintSet.RIGHT, layoutParams.leftMargin);
        }

        //right to left  end to start
        if (layoutParams.rightToLeft != -1 && layoutParams.endToStart != -1) {
            int rightToLeft = layoutParams.rightToLeft != -1 ? layoutParams.rightToLeft : layoutParams.endToStart;
            set.connect(addView.getId(), ConstraintSet.RIGHT, rightToLeft, ConstraintSet.LEFT, layoutParams.rightMargin);
        }

        //top to bottom
        if (layoutParams.topToBottom != -1)
            set.connect(addView.getId(), ConstraintSet.TOP, layoutParams.topToBottom, ConstraintSet.BOTTOM, layoutParams.topMargin);

        //bottom to top
        if (layoutParams.bottomToTop != -1)
            set.connect(addView.getId(), ConstraintSet.BOTTOM, layoutParams.bottomToTop, ConstraintSet.TOP, layoutParams.bottomMargin);


        set.constrainWidth(addView.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainHeight(addView.getId(), ConstraintSet.WRAP_CONTENT);

        set.applyTo(layout);

        bindView.setVisibility(View.INVISIBLE);
        addView.setVisibility(View.VISIBLE);

    }
}
