package com.me.harris.androidanimations._31_picker;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPickerViewBinding;
import com.me.harris.androidanimations.widget.ChooseCommunicateTimePicker;
import com.me.harris.androidanimations.widget.DatePicker;

/**
 * Created by Harris on 2017/3/17.
 */

public class PickerActivity extends BaseAppCompatActivity implements View.OnClickListener {

    ActivityPickerViewBinding binding;

    DatePicker picker;
    ChooseCommunicateTimePicker timePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picker_view);
        picker = binding.datePicker;
        timePicker = binding.timePicker;
//        Calendar curCal = Calendar.getInstance();
        picker.setDate(2008, 6, 17, 1900, 2200);
        timePicker.setDate(2017,3,18,2000);
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                binding.textView.setText("时间 " + picker.getYear() + "年" + picker.getMonth() + "月" + picker.getDay() + "日");
                break;
            case R.id.button2:
                binding.textView.setText("" + timePicker.getYear() + ":" + timePicker.getMonth() + ":" + timePicker.getDay());
                break;
            default:
                break;
        }
    }
}
