package com.hebe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * 圆形进度条.
 * Created by HebeChung on 16/7/4.
 */

public class CircleProgressBar extends ProgressBar{
    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
