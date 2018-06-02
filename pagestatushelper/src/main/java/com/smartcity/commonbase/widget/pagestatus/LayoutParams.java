package com.smartcity.commonbase.widget.pagestatus;

/**
 * Author: YuJunKui
 * Time:2018/1/16 15:30
 * Tips:
 */

public class LayoutParams {


    public int paddingLeft;
    public int paddingTop;
    public int paddingRight;
    public int paddingBottom;

    public int leftMargin;
    public int topMargin;
    public int rightMargin;
    public int bottomMargin;

    public int width;
    public int height;

    public boolean centerInParent;

    public void setPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
    }

    public static final int MATCH_PARENT = -1;

    public static final int WRAP_CONTENT = -2;

    public LayoutParams(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
