package com.me.harris.androidanimations.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;

/**
 * Created by Harris on 2017/2/25.
 */

public class ActivityDataBindingEntrance extends BaseAppCompatActivity {

    ActivityDataBindingEntranceBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_entrance);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,FragmentListTricks.newInstance(), FragmentListTricks.TAG).commitNow();
    }
}
