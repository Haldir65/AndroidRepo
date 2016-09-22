package com.me.harris.androidanimations.canvas.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCanvasBinding;
import com.me.harris.androidanimations.ui.recyclerview.adapter.MainActivityAdapter;
import com.me.harris.androidanimations.ui.recyclerview.itemDecoration.MainAdapterItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasActivity extends AppCompatActivity {

    ActivityCanvasBinding binding;
    private MainActivityAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        mAdapter = new MainActivityAdapter(R.layout.item_main);
        List<Pair<String, Class>> mDatas = new ArrayList<>();
        mDatas.add(new Pair<String, Class>("ViewAnimation", QQHealthViewActivityV1.class));
        mDatas.add(new Pair<String, Class>("DrawableAnimation", QQHealthViewActivityV2.class));
        mDatas.add(new Pair<String, Class>("PropertyAnimation", AirConditionerViewActivity.class));
        mDatas.add(new Pair<String, Class>("BouncingBalls", SesameCreditActivity.class));
        mDatas.add(new Pair<String, Class>("ShadowCard", ProgressBarViewActivity.class));
        mDatas.add(new Pair<String, Class>("CardImageView", LoadingLeafActivity.class));
        mDatas.add(new Pair<String, Class>("Path_Bezier", Path_BezierActivity.class));
        mAdapter.setmDatas(mDatas);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.addItemDecoration(new MainAdapterItemDecoration(this));
        binding.toolbar.setTitle(getClass().getSimpleName());

    }


}
