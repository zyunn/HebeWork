package com.hebe.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.localadmin.hebework.R;
import com.hebe.util.UiUtil;


/**
 * 波特大夫渡模式
 * Created by HebeChung on 16/7/6.
 */
public class PorterDuffXFerModeView extends View {

    private Paint mBitmapPaint;
    private Bitmap mSrcBitmap, mMaskBitmap;
    private PorterDuffXfermode mPorterDuffXfermode;
    private static final int WAVE_TRANS_SPEED = 4;
    private int mSpeed;
    private PaintFlagsDrawFilter mDrawFilter;
    private int mCurrentPosition = 0;
    private int mTotalWidth, mTotalHeight, mCentreX, mCentreY;
    private Rect mMaskSrcRect, mMaskDesRect;
    private Rect mSrcRect, mDesRect;
    private int mMaskWith,mMaskHeight;

    public PorterDuffXFerModeView(Context context) {
        super(context);
    }

    public PorterDuffXFerModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PorterDuffXFerModeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init() {
        initPaint();
        initBitmap();
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mSpeed = UiUtil.dip2Px(getContext(), WAVE_TRANS_SPEED);
        mDrawFilter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, Paint.DITHER_FLAG);

        new Thread() {
            @Override
            public void run() {
                int mSrcWidth = mSrcBitmap.getWidth();
                while (mCurrentPosition<mSrcWidth) {
                    mCurrentPosition += mSpeed;
//                    if (mCurrentPosition > mSrcWidth) {
//                        mCurrentPosition = 0;
//                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }.start();


    }

    /**
     * 初始化Bitap
     */
    private void initBitmap() {
        mSrcBitmap = ((BitmapDrawable) getContext().getResources().getDrawable(R.mipmap.wave_2000)).getBitmap();
        mMaskBitmap = ((BitmapDrawable) getContext().getResources().getDrawable(R.mipmap.wujiaoxin)).getBitmap();
        mMaskWith=mMaskBitmap.getWidth();
        mMaskHeight=mMaskBitmap.getHeight();
        mMaskSrcRect=new Rect(0,0,mMaskWith,mMaskHeight);
    }

    /***
     * 初始化Paint.
     * 开启防抖动和图像过滤
     */
    private void initPaint() {
        mBitmapPaint = new Paint();
        //防抖动
        mBitmapPaint.setDither(true);
        // 开启图像过滤
        mBitmapPaint.setFilterBitmap(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(mDrawFilter);
        canvas.drawColor(Color.TRANSPARENT);
        int sc = canvas.saveLayer(0, 0, mTotalWidth, mTotalHeight, null, Canvas.ALL_SAVE_FLAG);
        mSrcRect.set(mCurrentPosition, 0, mCurrentPosition + mCentreX, (int) (mTotalHeight*0.5));
        canvas.drawBitmap(mSrcBitmap, mSrcRect, mDesRect, mBitmapPaint);
        mBitmapPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawBitmap(mMaskBitmap, mMaskSrcRect, mMaskDesRect, mBitmapPaint);
        mBitmapPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCentreX = mTotalWidth / 2;
        mCentreY = mTotalHeight;
        mSrcRect = new Rect();
        mDesRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
        mMaskDesRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
    }
}
