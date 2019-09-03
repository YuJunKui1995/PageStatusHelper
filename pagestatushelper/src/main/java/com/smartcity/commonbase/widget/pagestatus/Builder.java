package com.smartcity.commonbase.widget.pagestatus;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author: YuJunKui
 * Time:2017/12/8 15:03
 * Tips:用来构建占位
 */
public class Builder {

    @ColorInt
    private int bgColor = Color.TRANSPARENT;

    @ColorInt
    private Integer textColor = null;
    @DimenRes
    private int textSize = 16;
    private CharSequence errorText = "加载失败~点击重试", errorText2 = "加载失败~", loadingText = "努力加载中~", emptyText = "空空如也~", networkText = "网络断线啦~点击重试", networkText2 = "网络断线啦~", noLoginText = "没有登录~";

    @DrawableRes
    private int errorImage, loadingImage, emptyImage, networkImage, noLoginImage;

    @LayoutRes
    private int errorLayout, loadingLayout, emptyLayout, networkLayout, noLoginLayout;

    private View errorView, loadingView, emptyView, networkView, noLoginView;

    private Context context;

    private String bindViewBgColor = null;

    private View bindView;

    private LayoutParams params;

    /**
     * 此方法仅供PageStatusHelper类调用
     * 外部不可调用
     *
     * @param bindViewBgColor
     */
    public void setBindViewBgColor(String bindViewBgColor) {
        if (this.bindViewBgColor == null) {
            this.bindViewBgColor = bindViewBgColor;
        }
    }

    public void setBindView(View bindView) {
        this.bindView = bindView;
    }

    public Builder(Context context) {
        this.context = context;
        params = new LayoutParams();
    }

    public Builder setErrorText(CharSequence errorText) {
        this.errorText = errorText;
        return this;
    }

    public Builder setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;
    }


    /**
     * 不支持透明色
     *
     * @param bgColor
     * @return
     */
    public Builder setBgColor(@ColorInt int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public Builder setBgColor(String bgColor) {
        return setBgColor(Color.parseColor(bgColor));
    }


    public Builder setLoadingText(CharSequence loadingText) {
        this.loadingText = loadingText;
        return this;
    }

    public Builder setEmptyText(CharSequence emptyText) {
        this.emptyText = emptyText;
        return this;
    }

    public Builder setNetworkText(CharSequence networkText) {
        this.networkText = networkText;
        return this;
    }

    public Builder setNoLoginText(CharSequence noLoginText) {
        this.noLoginText = noLoginText;
        return this;
    }

    public Builder setErrorImage(@DrawableRes int errorImage) {
        this.errorImage = errorImage;
        return this;
    }

    public Builder setLoadingImage(@DrawableRes int loadingImage) {
        this.loadingImage = loadingImage;
        return this;
    }

    public Builder setEmptyImage(@DrawableRes int emptyImage) {
        this.emptyImage = emptyImage;
        return this;
    }

    public Builder setNetworkImage(@DrawableRes int networkImage) {
        this.networkImage = networkImage;
        return this;
    }

    public Builder setNoLoginImage(@DrawableRes int noLoginImage) {
        this.noLoginImage = noLoginImage;
        return this;
    }

    public Builder setErrorLayout(@LayoutRes int errorLayout) {
        this.errorLayout = errorLayout;
        return this;
    }

    public Builder setLoadingLayout(@LayoutRes int loadingLayout) {
        this.loadingLayout = loadingLayout;
        return this;
    }

    public Builder setEmptyLayout(@LayoutRes int emptyLayout) {
        this.emptyLayout = emptyLayout;
        return this;
    }

    public Builder setNetworkLayout(@LayoutRes int networkLayout) {
        this.networkLayout = networkLayout;
        return this;
    }

    public Builder setNoLoginLayout(@LayoutRes int noLoginLayout) {
        this.noLoginLayout = noLoginLayout;
        return this;
    }


    public Builder setNetworkView(View networkView) {
        this.networkView = networkView;
        return this;
    }

    public Builder setNoLoginView(View noLoginView) {
        this.noLoginView = noLoginView;
        return this;
    }

    public Builder setErrorView(View errorView) {
        this.errorView = errorView;
        return this;
    }

    public Builder setLoadingView(View loadingView) {
        this.loadingView = loadingView;
        return this;
    }

    public Builder setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        return this;
    }


    public Builder setLayoutParams(LayoutParams params) {
        this.params = params;
        return this;
    }

    public LayoutParams getLayoutParams() {
        return params;
    }

    public View buildErrorView(boolean isRetry) {

        errorView = getView(errorView, errorLayout, errorImage, errorText);

        if (errorView instanceof TextView && !isRetry) {
            ((TextView) errorView).setText(errorText2);
        }

        return errorView;
    }

    public View buildNoLoginView() {

        return noLoginView = getView(noLoginView, noLoginLayout, noLoginImage, noLoginText);
    }

    public View buildEmptyView() {

        return emptyView = getView(emptyView, emptyLayout, emptyImage, emptyText);
    }

    public View buildNetWorkView(boolean isRetry) {
        networkView = getView(networkView, networkLayout, networkImage, networkText);
        if (networkView instanceof TextView && !isRetry) {
            ((TextView) networkView).setText(networkText2);
        }

        return networkView;
    }


    public View buildLoadingView() {

        return loadingView = getView(loadingView, loadingLayout, loadingImage, loadingText);
    }

    private View getView(View view, int layout, int image, CharSequence text) {
        if (view == null) {
            //没有实例化

            //优先级 layout->image->text
            if (layout != 0) {

                view = LayoutInflater.from(context).inflate(layout, (ViewGroup) bindView.getParent(), false);

            } else if (image != 0) {

                ImageView imageView = new ImageView(context);
                imageView.setImageResource(image);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(params.imageWidth, params.imageHeight));
                view = imageView;
            } else {

                //字体颜色的获取
                //如果没有配置 取bindview的反转色
                if (textColor == null) {
                    //调用者没有配置颜色
                    //尝试使用bindview的反转色
                    if (bindViewBgColor != null) {

                        try {
                            textColor = Color.parseColor(ColorUtils.reserveColor(bindViewBgColor));
                        } catch (Exception e) {
                            textColor = Color.WHITE;
                        }
                    } else {
//                        bindview的反射色没取到
//                        设置默认白色
                        textColor = Color.WHITE;
                    }
                }

                TextView textView = new TextView(context);
                textView.setText(text);
                textView.setTextColor(textColor);
                textView.setTextSize(textSize);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(new ViewGroup.LayoutParams(bindView.getWidth(), bindView.getHeight()));
                view = textView;

            }

            if (bgColor != Color.TRANSPARENT) {
                view.setBackgroundColor(bgColor);
            }
        } else if (view instanceof TextView) {

            ((TextView) view).setText(text);
        }

        return view;
    }

}