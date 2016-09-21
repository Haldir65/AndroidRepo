package com.me.harris.androidanimations.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityMainBinding;
import com.me.harris.androidanimations.ui.recyclerview.adapter.MainActivityAdapter;
import com.me.harris.androidanimations.ui.recyclerview.itemDecoration.MainAdapterItemDecoration;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;
    private MainActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAdapter = new MainActivityAdapter(R.layout.item_main);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        binding.recyclerView.addItemDecoration(new MainAdapterItemDecoration(this));
        binding.toolbar.setTitle(getClass().getSimpleName());
    }



}
