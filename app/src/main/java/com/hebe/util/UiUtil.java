package com.hebe.util;

import android.content.Context;

/**
 * UI Utils UI工具类
 * Created by HebeChung on 16/7/5.
 */

public class UiUtil {
    /**
     * 将dp 转化成px.
     * 获取屏幕像素密度,dp*像素密度
     * 为避免四舍五入,加0.5f
     *
     * @param context context
     * @param dpValue dp值
     * @return px 值
     */
    public static int dip2Px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将dp 转化成px.
     * 获取屏幕像素密度,dp/像素密度
     * 为避免四舍五入,加0.5f
     *
     * @param context context
     * @param pxValue px值
     * @return px 值
     */
    public static int px2Dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
