package com.me.harris.androidanimations.apidemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityMaterialBinding;

/**
 * Created by Harris on 2016/9/14.
 */

public class MaterialWitness extends BaseAppCompatActivity {

    ActivityMaterialBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_material);
    }

}
