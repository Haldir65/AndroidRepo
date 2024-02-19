package com.me.harris.androidanimations._08_coordinateLayout;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCoordinateEntranceBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * Created by Harris on 2016/10/12.
 */

public class CoordinateLayoutEntrance extends BaseAppCompatActivity implements ActionCallBack {
    ActivityCoordinateEntranceBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coordinate_entrance);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        int id = view.getId();
        if (id == R.id.button1) {
            Intent intent1 = new Intent(this, CoordinateLayoutActivityOne.class);
            startActivity(intent1);
        } else if (id == R.id.button2) {
            Intent intent2 = new Intent(this, CoordinateLayoutActivityTwo.class);
            startActivity(intent2);
        } else if (id == R.id.button3) {
        } else if (id == R.id.button4) {
        } else if (id == R.id.button5) {
        }
    }
}
