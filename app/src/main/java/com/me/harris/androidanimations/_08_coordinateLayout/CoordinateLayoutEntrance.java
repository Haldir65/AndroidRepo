package com.me.harris.androidanimations._08_coordinateLayout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCoordinateEntranceBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * Created by Harris on 2016/10/12.
 */

public class CoordinateLayoutEntrance extends AppCompatActivity implements ActionCallBack {
    ActivityCoordinateEntranceBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coordinate_entrance);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent1 = new Intent(this, CoordinateLayoutActivityOne.class);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, CoordinateLayoutActivityTwo.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            default:
                break;
        }
    }
}
