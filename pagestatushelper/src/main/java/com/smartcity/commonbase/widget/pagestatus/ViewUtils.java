package com.smartcity.commonbase.widget.pagestatus;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/**
 * Author: YuJunKui
 * Time:2017/12/9 9:41
 * Tips:
 */

public class ViewUtils {

    private static final String TAG = "ViewUtils";

    /**
     * 测量view方法
     *
     * @param view
     * @return int[0] width int[1] height
     */
    public static int[] getViewMetrics(View view) {

        int[] ints = new int[2];

        ints[0] = view.getWidth();
        ints[1] = view.getHeight();

        if (ints[0] == 0 || ints[1] == 0) {
            try {
                view.measure(0, 0);
            } catch (Exception e) {
            }
            ints[0] = view.getMeasuredWidth();
            ints[1] = view.getMeasuredHeight();
        }

        return ints;
    }

    /**
     * 简单处理下有些情况下异常的问题
     *
     * @param view
     * @return int[0] width int[1] height
     */
    public static int[] getNormalBindViewMetric(View view) {

        int[] ints = getViewMetrics(view);

        //目前这种测量方法不好 兼容下获取父类的
        if (ints[0] == 0 && ints[1] == 0) {
            View parent = (View) view.getParent();
            if (parent != null) {
                return getViewMetrics(parent);
            }
        }
        return ints;
    }


    /**
     * 这里会处理如果bindView的某个父类是CoordinatorLayout的话
     * 将正确高度 CoordinatorLayout-AppBarLayout 返回
     * @param bindView
     * @return
     */
    public static int[] getBindViewMetrics(View bindView) {

        CoordinatorLayout parent = findCoordinatorLayoutParent(bindView);
        if (parent != null) {
            int[] ints;

            //寻找AppBarLayout
            AppBarLayout appBarLayout = null;
            for (int i = 0; i < parent.getChildCount(); i++) {
                View childAt = parent.getChildAt(i);
                if (childAt instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout) childAt;
                    break;
                }
            }

            if (appBarLayout == null) {
                return getNormalBindViewMetric(bindView);
            }

            ints=new int[]{parent.getWidth(),parent.getHeight()-appBarLayout.getHeight()};
            return ints;
        } else {
            return getNormalBindViewMetric(bindView);
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param v
     * @return
     */
    public static CoordinatorLayout findCoordinatorLayoutParent(View v) {

        View parent;

        if (v == null || v.getParent()==null|| ! (v.getParent() instanceof View)) {
            return null;
        }

        parent= (View) v.getParent();

        if (parent instanceof CoordinatorLayout) {
            return (CoordinatorLayout) parent;
        } else {
            return findCoordinatorLayoutParent(parent);
        }
    }


    /**
     * 父view是否是CoordinatorLayout(会递归至null)
     *
     * @param v
     * @return
     */
    public static boolean parentCoordinatorLayout(View v) {

        return findCoordinatorLayoutParent(v) != null;
    }

}
