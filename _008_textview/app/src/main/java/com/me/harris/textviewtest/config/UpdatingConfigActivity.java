package com.me.harris.textviewtest.config;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import com.me.harris.textviewtest.R;

import java.util.Locale;

import io.reactivex.Observable;

public class UpdatingConfigActivity extends AppCompatActivity {


    TextView mTextView;
    Button mButtonzh,mButtonen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actovity_config_locale);
        mTextView = findViewById(R.id.textView3);
        mButtonen = findViewById(R.id.btn_en);
        mButtonzh = findViewById(R.id.btn_zn);
        mTextView.setText(R.string.default_tag_name);


        mButtonzh.setOnClickListener(
                v -> {
//                    Resources resources = v.getContext().getResources();
//                    DisplayMetrics dm = resources.getDisplayMetrics();
//                    Configuration config = resources.getConfiguration();
//                    config.locale = Locale.SIMPLIFIED_CHINESE;
//                    resources.updateConfiguration(config, dm);
//                    mTextView.setText(R.string.default_tag_name);
                    mTextView.setSelected(true);

                }
        );

        mButtonen.setOnClickListener(
                v -> {
//                    Resources resources = v.getContext().getResources();
//                    DisplayMetrics dm = resources.getDisplayMetrics();
//                    Configuration config = resources.getConfiguration();
//                    config.locale = Locale.ENGLISH;
//                    resources.updateConfiguration(config, dm);
//                    mTextView.setText(R.string.default_tag_name);
                    mTextView.setSelected(false);
                }
        );

        addStateListChange();

    }

    void addStateListChange(){
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_selected} , // pressed
                new int[] {-android.R.attr.state_selected} , // unpressed
                new int[]{}
        };

        int[] colors = new int[] {
                Color.RED,
                Color.GREEN,
                Color.YELLOW,
        };

        ColorStateList myList = new ColorStateList(states, colors);
        mTextView.setTextColor(myList);
        mTextView.setClickable(true);

    }
}
