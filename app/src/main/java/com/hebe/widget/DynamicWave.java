package com.hebe.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import com.hebe.util.UiUtil;

/**
 * 自定义水波纹效果
 * Created by HebeChung on 16/7/5.
 */

public class DynamicWave extends View {
    /**
     * 波文颜色
     */
    private static final int WAVE_PAINT_COLOR = 0x880000aa;
    private static final float STRETCH_FATOR_A = 20;
    private static final int OFFSET_Y = 0;
    private static final int TRANSLATE_X_SPEED_ONE = 2;
    private static final int TRANSLATE_X_SPEED_TWO = 1;
    private float[] mResetOneYPositon;
    private float[] mResetTwoYPosition;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int xOneOffSet = 0;
    private int xTwoOffSet = 0;

    private Paint mWavePaint;
    private DrawFilter mDrawFilter;
    private Paint mCirclePaint;


    public DynamicWave(Context context) {
        super(context);

    }

    public DynamicWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DynamicWave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /***
     * 初始化
     */
    private void init() {
        // 将速度值转成px ,保证再所有设备上都保持一只
        // TODO: 16/7/5 这里可以转成用xml 控制
        mXOffsetSpeedOne = UiUtil.dip2Px(getContext(), TRANSLATE_X_SPEED_ONE);
        mXOffsetSpeedTwo = UiUtil.dip2Px(getContext(), TRANSLATE_X_SPEED_TWO);
        mWavePaint = new Paint();
        //消除锯齿
        mWavePaint.setAntiAlias(true);
        mWavePaint.setColor(WAVE_PAINT_COLOR);
        //设置实线
        mWavePaint.setStyle(Paint.Style.FILL);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mCirclePaint=new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(0x000000);
    }

    private int mTotalWidth;
    private int mTotalHeight;
    private float[] mYPositions;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(mDrawFilter);
        resetPositionY();
        for (int i = 0; i < mTotalWidth; i++) {
            canvas.drawLine(i, mTotalHeight - mResetOneYPositon[i] - mTotalHeight/2 ,i, mTotalHeight, mWavePaint);
            canvas.drawLine(i, mTotalHeight - mResetTwoYPosition[i] - mTotalHeight/2, i, mTotalHeight, mWavePaint);
        }
        canvas.drawCircle(mTotalWidth/2,mTotalHeight/2,mTotalHeight/2,mWavePaint);
        xOneOffSet += mXOffsetSpeedOne;
        xTwoOffSet += mXOffsetSpeedTwo;
        if (xOneOffSet >= mTotalWidth) {
            xOneOffSet = 0;
        }
        if (xTwoOffSet >= mTotalWidth) {
            xTwoOffSet = 0;
        }
        postInvalidate();

    }

    private void resetPositionY() {
        int yOneInterval = mYPositions.length - xOneOffSet;
        System.arraycopy(mYPositions, xOneOffSet, mResetOneYPositon, 0, yOneInterval);
        System.arraycopy(mYPositions, 0, mResetOneYPositon, yOneInterval, xOneOffSet);
        int yTwoInterval = mYPositions.length - xTwoOffSet;
        System.arraycopy(mYPositions, xTwoOffSet, mResetTwoYPosition, 0, yTwoInterval);
        System.arraycopy(mYPositions, 0, mResetTwoYPosition, yTwoInterval, xTwoOffSet);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mYPositions = new float[mTotalWidth];
        mResetOneYPositon = new float[mTotalWidth];
        mResetTwoYPosition = new float[mTotalWidth];
        float mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);
        for (int i = 0; i < mTotalWidth; i++) {
            mYPositions[i] = (float) (STRETCH_FATOR_A * Math.sin(mCycleFactorW * i + OFFSET_Y));
        }
    }
}
