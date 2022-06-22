package com.me.harris.androidanimations._41_span;

import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivitySpanBinding;

/**
 * Created by Fermi on 2017/7/16.
 */

public class SpannableActivity extends BaseAppCompatActivity {

    ActivitySpanBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_span);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int unicodeJoy = 0x1F602;
        String emojiString = getEmojiStringByUnicode(unicodeJoy);
        binding.text.setText(emojiString);
        binding.text.setText(getEmojiStringByUnicode(unicodeJoy));
        SpannableString sp = new SpannableString("wwww.baidu.com");
        sp.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.text.append(sp);

    }



    private String getEmojiStringByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }



}
