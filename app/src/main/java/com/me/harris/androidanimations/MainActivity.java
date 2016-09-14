package com.me.harris.androidanimations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.me.harris.androidanimations.databinding.ActivityMainBinding;
import com.me.harris.androidanimations.drawableAnimations.DrawableAnimationActivity;
import com.me.harris.androidanimations.propertyanimations.PropertyAnimationActivity;
import com.me.harris.androidanimations.utils.ActionCallBack;
import com.me.harris.androidanimations.viewAnimations.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity implements ActionCallBack {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button1:
                intent.setClass(this, ViewAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(this, DrawableAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent.setClass(this, PropertyAnimationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
