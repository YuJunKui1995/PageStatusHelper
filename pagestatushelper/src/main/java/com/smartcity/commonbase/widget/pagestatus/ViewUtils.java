package com.smartcity.commonbase.widget.pagestatus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Author: YuJunKui
 * Time:2017/12/9 9:41
 * Tips:
 */

public class ViewUtils {

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
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
