package com.hebe.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 自定义View测试View里几个方法的调用顺序.
 * Created by  HebeChung on 16/6/23.
 */

public class MyViewTest extends View {
    private static final String TAG="MyViewTest";
    public MyViewTest(Context context) {
        super(context);
    }

    public MyViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
    }
}
