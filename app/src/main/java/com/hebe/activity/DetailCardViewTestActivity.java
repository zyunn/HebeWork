package com.hebe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.localadmin.hebework.R;

/**
 * 第三个View
 * 测试 CoordLayout 中间有ImageView ,FloatingActionButton,CardView
 * Created by  HebeChung on 16/6/21.
 */

public class DetailCardViewTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detatil_cardview_test);
        final Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbarLayout.setTitle("HebeChung 's Work");
        ImageView imageView= (ImageView) findViewById(R.id.image);
        imageView.setImageResource(R.drawable.cheese_3);
    }
}
