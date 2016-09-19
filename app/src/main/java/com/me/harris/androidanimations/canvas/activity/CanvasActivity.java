package com.me.harris.androidanimations.canvas.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCanvasBinding;
import com.me.harris.androidanimations.utils.ActionCallBack;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasActivity extends AppCompatActivity implements ActionCallBack {

    ActivityCanvasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        setTitle("canvas");
        binding.setOnClickListener(this);
    }

    @Override
    public void onClickView(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button1:
                intent.setClass(this, QQHealthViewActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
