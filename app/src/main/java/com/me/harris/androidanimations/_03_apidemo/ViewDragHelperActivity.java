package com.me.harris.androidanimations._03_apidemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityViewDragBinding;

/**
 * Created by Fermi on 2016/10/2.
 */

public class ViewDragHelperActivity extends BaseAppCompatActivity {
    ActivityViewDragBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_drag);
        handleStatusBar();
    }
}
