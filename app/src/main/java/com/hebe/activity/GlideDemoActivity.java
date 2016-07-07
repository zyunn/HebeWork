package com.hebe.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.localadmin.hebework.R;

/**
 * Glide 测试Demo.
 * 测试glide的使用和性能
 * 学习实现远离
 * Created by HebeChung on 16/6/29.
 */

public class GlideDemoActivity extends AppCompatActivity {
    private ImageView circle1;
    private int method=1;

    private static final String imgUrl="http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        method=2;
        ImageView imageView = (ImageView) findViewById(R.id.image);
        circle1 = (ImageView) findViewById(R.id.image_circle1);
        ImageView circle2 = (ImageView) findViewById(R.id.image_circle2);
        String s = Glide.getPhotoCacheDir(getBaseContext()).getAbsolutePath();
        Log.e("TAG->","Glide cache Disk path-->" +s);
        if (imageView != null) {
            Glide.with(getBaseContext()).load(imgUrl)
                    .crossFade()
                    .into(imageView);
        }
        // 加载圆形图片方法一
        Glide.with(getBaseContext()).load(imgUrl)
                .asBitmap()
                .placeholder(R.drawable.cheese_3)
                .centerCrop().into(new BitmapImageViewTarget(circle1) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                circleDrawable.setCircular(true);
                circle1.setImageDrawable(circleDrawable);
            }
        });
        //  加载圆形图片方法二
        if (circle2 != null) {
            Glide.with(getBaseContext()).load(imgUrl)
                    .placeholder(R.drawable.cheese_3)
                    .transform(new CircleTransFrom(getBaseContext())).into(circle2);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (1==method){
            clearGlideCacheInBackgroundThread1();
        } else {
            clearGlideCacheInBackgroundThread2();
        }

    }

    /**
     * 第一种运行再后台线程中的
     */
    private void clearGlideCacheInBackgroundThread1(){
        HandlerThread backgroundHandlerThread=new HandlerThread("backgroundThread");
        backgroundHandlerThread.start();
        new Handler(backgroundHandlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                clearGlideDiskCache();
            }
        });
    }

    /**
     * 第二种运行再background线程中
     */
    private void clearGlideCacheInBackgroundThread2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                clearGlideDiskCache();
            }
        }).start();
    }


    /**
     * 清除glide 磁盘缓存
     */
    private void clearGlideDiskCache() {
        Glide.get(this).clearDiskCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 自己写圆形ImageView的TransFormation 类.
     * 相当重新弄一个圆形的Canvas ,然后把图片重新绘制成圆形
     * 这种效果比第一个效果好太多了
     */
    private class CircleTransFrom extends BitmapTransformation {

        CircleTransFrom(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap resource) {
            if (null == resource) {
                return null;
            }
            int size = Math.min(resource.getHeight(), resource.getWidth());
            int x = (resource.getWidth() - size) / 2;
            int y = (resource.getHeight() - size) / 2;
            Bitmap square = Bitmap.createBitmap(resource, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(square, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }

    }

}
