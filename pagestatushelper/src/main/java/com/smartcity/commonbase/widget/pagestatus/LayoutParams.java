package com.smartcity.commonbase.widget.pagestatus;

import android.view.View;
import android.view.ViewGroup;

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

    public int imageWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    public int imageHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

    public boolean centerInParent;

    public void setPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
    }

    /**
     * 只有在图片模式下才有用
     *
     * @param imageWidth
     * @param imageHeight
     */
    public LayoutParams(int imageWidth, int imageHeight) {
        this();
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void setWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public LayoutParams() {
        centerInParent=true;
    }
}
