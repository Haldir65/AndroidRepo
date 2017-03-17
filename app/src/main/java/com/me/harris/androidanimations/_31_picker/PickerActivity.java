package com.me.harris.androidanimations._31_picker;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.utils.LogUtil;
import com.me.harris.androidanimations.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Harris on 2017/3/17.
 */

public class PickerActivity extends BaseAppCompatActivity {

    private DatePicker picker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picker = new DatePicker(this);
        Calendar curCal = Calendar.getInstance();
        picker.setDate(2008,6,17,1900,2200);
        setContentView(picker);
    }

    @Override
    public void onBackPressed() {
        LogUtil.d("");
    }
}
