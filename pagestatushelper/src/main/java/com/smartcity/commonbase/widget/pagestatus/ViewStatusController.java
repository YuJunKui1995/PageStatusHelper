package com.smartcity.commonbase.widget.pagestatus;

import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.smartcity.commonbase.widget.pagestatus.imp.ConstraintLayoutViewStatusImp;
import com.smartcity.commonbase.widget.pagestatus.imp.FrameLayoutViewStatusImp;
import com.smartcity.commonbase.widget.pagestatus.imp.LinearLayoutViewStatusImp;
import com.smartcity.commonbase.widget.pagestatus.imp.RelativeLayoutViewStatusImp;
import com.smartcity.commonbase.widget.pagestatus.imp.ViewGroupViewStatusImp;
import com.smartcity.commonbase.widget.pagestatus.imp.ViewStatusInterface;

/**
 * Author: YuJunKui
 * Time:2017/12/8 10:38
 * Tips:
 */

public class ViewStatusController {

    private ViewStatusInterface viewStatusInterface;
    private View bindView;
    private View addView;

    public void showViewStatus(View bindView, View addView, LayoutParams params) {

        this.bindView = bindView;
        this.addView = addView;

        if (params == null) {
            //默认居中
            params = new LayoutParams(LayoutParams.WRAP_CONTENT
                    , LayoutParams.WRAP_CONTENT);
            params.centerInParent = true;
        }

        ViewGroup parent = (ViewGroup) bindView.getParent();

        if (parent instanceof RelativeLayout) {

            viewStatusInterface = new RelativeLayoutViewStatusImp();
        } else if (parent instanceof LinearLayout) {

            viewStatusInterface = new LinearLayoutViewStatusImp();
        } else if (parent instanceof FrameLayout) {

            viewStatusInterface = new FrameLayoutViewStatusImp();
        } else if (parent instanceof ConstraintLayout) {

            viewStatusInterface = new ConstraintLayoutViewStatusImp();
        } else if (parent instanceof ViewPager||parent instanceof ScrollView) {

            throw new RuntimeException("不支持直属父布局为"+parent.getClass().getSimpleName()+"使用PageStatusHelper，解决方案暂为在 binview外套一层布局");
        } else {
//            throw new RuntimeException("不支持" + bindView.getParent().getClass().getSimpleName() + "布局使用PageStatusHelper，联系维护者");
            viewStatusInterface = new ViewGroupViewStatusImp();
        }
        viewStatusInterface.addStatusView(bindView, addView, params);

    }

    public void showContent() {

        if (viewStatusInterface == null) return;
        viewStatusInterface.showContentView(bindView, addView);

    }

}
