package com.me.harris.androidanimations._23_TitanicTextView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityTitanicTextviewBinding;

/**
 * Created by Harris on 2017/1/14.
 */

public class TitanicTextViewActivity extends BaseAppCompatActivity {
    ActivityTitanicTextviewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_titanic_textview);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // // set fancy typeface
        binding.titanic.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        new Titanic().start(binding.titanic);

    }
}
