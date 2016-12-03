package com.me.harris.androidanimations._14_popup_Window;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPopUpWindowBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * Created by Harris on 2016/12/3.
 */

public class PopupWindowActivity extends BaseAppCompatActivity implements ActionCallBack {
    ActivityPopUpWindowBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pop_up_window);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                break;
            case R.id.button_2:
                break;
            case R.id.button3:
                break;
            default:
                break;
        }
    }
}
