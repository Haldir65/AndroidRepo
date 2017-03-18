package com.me.harris.androidanimations._31_picker;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPickerViewBinding;
import com.me.harris.androidanimations.widget.ChooseCommunicateTimePicker;
import com.me.harris.androidanimations.widget.DatePicker;

/**
 * Created by Harris on 2017/3/17.
 */

public class PickerActivity extends BaseAppCompatActivity implements View.OnClickListener, ChooseCommunicateTimePicker.onDateChangeListener, DialogInterface.OnDismissListener ,DialogInterface.OnClickListener{

    ActivityPickerViewBinding binding;

    DatePicker picker;

    int mYear, mMonth, mDay;
    private AlertDialog dialog;
    private ChooseCommunicateTimePicker picker1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picker_view);
        picker = binding.datePicker;
//        Calendar curCal = Calendar.getInstance();
        picker.setDate(2008, 6, 17, 1900, 2200);
        mYear = 2016;
        mMonth = 3;
        mDay = 31;
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
                if (dialog == null) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    dialog = alertDialogBuilder.create();
                    alertDialogBuilder.setTitle("选择事件");//
                    dialog.setCanceledOnTouchOutside(true);
                    picker1 = new ChooseCommunicateTimePicker(this);
                    picker1.monDateChangeListener = this;
                    dialog.setView(picker1);
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定", (DialogInterface.OnClickListener) PickerActivity.this);
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", (DialogInterface.OnClickListener) PickerActivity.this);
                    dialog.getWindow().setGravity(Gravity.BOTTOM);
                    dialog.setOnDismissListener(this);
                }
                picker1.setDate(mYear, mMonth, mDay, 2000);
                dialog.show();//将dialog显示出来
//                binding.textView.setText("" + timePicker.getYear() + ":" + timePicker.getMonth() + ":" + timePicker.getDay());
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateChanged(ChooseCommunicateTimePicker view, int year, int monthOfYear, int dayOfMonth) {
        binding.textView.setText(year + "年" + monthOfYear + "月" + dayOfMonth+"日");
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
