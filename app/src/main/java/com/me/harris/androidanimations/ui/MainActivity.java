package com.me.harris.androidanimations.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.apidemo.BouncingBalls;
import com.me.harris.androidanimations.apidemo.MaterialWitness;
import com.me.harris.androidanimations.apidemo.ShadowCardDrag;
import com.me.harris.androidanimations.canvas.activity.CanvasActivity;
import com.me.harris.androidanimations.databinding.ActivityMainBinding;
import com.me.harris.androidanimations.drawableAnimations.DrawableAnimationActivity;
import com.me.harris.androidanimations.propertyanimations.PropertyAnimationActivity;
import com.me.harris.androidanimations.ui.recyclerview.adapter.MainActivityAdapter;
import com.me.harris.androidanimations.ui.recyclerview.itemDecoration.MainAdapterItemDecoration;
import com.me.harris.androidanimations.viewAnimations.ViewAnimationActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MainActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAdapter = new MainActivityAdapter(R.layout.item_main);
        List<Pair<String, Class>> list = new ArrayList<>();
        list.add(new Pair<String, Class>("ViewAnimation", ViewAnimationActivity.class));
        list.add(new Pair<String, Class>("DrawableAnimation", DrawableAnimationActivity.class));
        list.add(new Pair<String, Class>("PropertyAnimation", PropertyAnimationActivity.class));
        list.add(new Pair<String, Class>("BouncingBalls", BouncingBalls.class));
        list.add(new Pair<String, Class>("ShadowCard", ShadowCardDrag.class));
        list.add(new Pair<String, Class>("CardImageView", MaterialWitness.class));
        list.add(new Pair<String, Class>("Canvas", CanvasActivity.class));
        mAdapter.setmDatas(list);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.addItemDecoration(new MainAdapterItemDecoration(this));
        binding.toolbar.setTitle(getClass().getSimpleName());
    }


}
