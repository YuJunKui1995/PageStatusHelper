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

        if (lastAddView != null) {
            lastAddView.setVisibility(View.GONE);
        }
        lastAddView = addView;

        if (addView.getTag() == null) {

            if (params.centerInParent == true) {


                addView.post(new Runnable() {
                    @Override
                    public void run() {

                        //说明在一瞬间调用了两次状态 有可能前一次绘制计算位置还没完成就 调用了第二次
                        //一般出现在没有网络的情况下，
                        // gone后没有绘制完成的会没有宽高
                        //如果用INVISIBLE会导致线性布局出现问题
                        //所以在下次调用的时候直接gone，
                        // 当第一次的状态下次再调用的时候就会当做第一次显示，再次走这里
                        if (ViewGroupViewStatusImp.this.addView != addView) {
                            //没有摆放成功就调用了其他的状态,需要删除view
                            ((ViewGroup) addView.getParent()).removeView(addView);
                            return;
                        }

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
            if (addView.getParent() == null) parent.addView(addView);
            hideContent(bindView);
        } else {
            lastAddView = addView;
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
