package com.me.harris.androidanimations._08_coordinateLayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCoordinateOneBinding;

/**
 * Created by Fermi on 2016/10/11.
 */

public class CoordinateLayoutActivityOne extends AppCompatActivity {
    ActivityCoordinateOneBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coordinate_one);
    }
}
