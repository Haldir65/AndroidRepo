package com.me.harris.androidanimations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.me.harris.androidanimations.apidemo.BouncingBalls;
import com.me.harris.androidanimations.apidemo.MaterialWitness;
import com.me.harris.androidanimations.apidemo.ShadowCardDrag;
import com.me.harris.androidanimations.canvas.CanvasActivity;
import com.me.harris.androidanimations.databinding.ActivityMainBinding;
import com.me.harris.androidanimations.drawableAnimations.DrawableAnimationActivity;
import com.me.harris.androidanimations.propertyanimations.PropertyAnimationActivity;
import com.me.harris.androidanimations.utils.ActionCallBack;
import com.me.harris.androidanimations.utils.ToastUtils;
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
                break;
            case R.id.button2:
                intent.setClass(this, DrawableAnimationActivity.class);
                break;
            case R.id.button3:
                intent.setClass(this, PropertyAnimationActivity.class);
                break;
            case R.id.button4:
                intent.setClass(this, BouncingBalls.class);
                break;
            case R.id.button5:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    ToastUtils.showTextShort(this,"Shadow only effect on Api 21 above !");
                    return;
                }
                intent.setClass(this, ShadowCardDrag.class);
                break;
            case R.id.button6:
                intent.setClass(this, MaterialWitness.class);
                break;
            case R.id.button7:
                intent.setClass(this, CanvasActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
