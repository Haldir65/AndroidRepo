package com.me.harris.androidanimations._09_recyclerView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._09_recyclerView.adapter.PlainAdapter;
import com.me.harris.androidanimations.databinding.ActivityDiffUtilBinding;

/**
 * Created by Harris on 2017/2/12.
 */

public class DiffUtilActivity extends BaseAppCompatActivity {
    ActivityDiffUtilBinding binding;
    PlainAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diff_util);
        setSupportActionBar(binding.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAdapter = new PlainAdapter();
        binding.recyclerView.setAdapter(mAdapter);
    }


}
