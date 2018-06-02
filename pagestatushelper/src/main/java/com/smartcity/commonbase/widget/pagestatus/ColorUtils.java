package com.smartcity.commonbase.widget.pagestatus;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Author: YuJunKui
 * Time:2018/5/14 15:31
 * Tips:
 */
public class ColorUtils {

    /**
     * 颜色反转
     * 比如输入白色 返回黑色
     * 颜色转16进制后 15减去即可
     *
     * @param originalColor 原来的颜色  #******格式或者 #********  带透明值
     * @return 返回反转后的颜色
     */
    public static String reserveColor(String originalColor) {
        StringBuilder sb = new StringBuilder().append("#");

        if (!originalColor.startsWith("#")){
            originalColor="#"+originalColor;
        }
        //==9则 带了透明值  消除透明值
        int start = originalColor.length() == 9 ? 3 : 1;

        for (int i = start; i < originalColor.length(); i++) {
            String st = originalColor.charAt(i) + "";
            int temp = Integer.parseInt(st, 16);
            sb.append("" + Integer.toHexString(15 - temp).toUpperCase());
        }
        return sb.toString();
    }


    /**
     * @param v 需要获取的控件
     * @return 返回值
     */
    public static String obtainBgColor(View v) {

        try {
            Drawable background = v.getBackground();
            if (background == null) {
                return obtainBgColor((View) v.getParent());
            } else {
                ColorDrawable colorDrawable = (ColorDrawable) background;
                int color = colorDrawable.getColor();
                return intColorConvert16Color(color);
            }
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * int 颜色值转化为16进制颜色值
     *
     * @param color
     * @return
     */
    public static String intColorConvert16Color(int color) {
        String R, G, B;
        StringBuffer sb = new StringBuffer();
        R = Integer.toHexString(Color.red(color));
        G = Integer.toHexString(Color.green(color));
        B = Integer.toHexString(Color.blue(color));
        //判断获取到的R,G,B值的长度 如果长度等于1 给R,G,B值的前边添0
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        sb.append("0x");
        sb.append(R);
        sb.append(G);
        sb.append(B);
        return sb.toString();
    }

}
