package com.me.harris.androidanimations._01_drawable.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentClipDrawableBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Fermi on 2016/10/8.
 */

public class ClipDrawableFragment extends Fragment {
    public static final int IS_CONTUNUE = 0X22;
    FragmentClipDrawableBinding binding;
    Timer timer = new Timer();
    private ClipDrawable drawable;
    final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == IS_CONTUNUE) {
                /**setlevel()设置图片截取的大小
                 * 修改ClipDrawable的level值，level值为0--10000；
                 * 10000：截取图片大小为空白，0：截取图片为整张图片；
                 */
                drawable.setLevel(drawable.getLevel() + 200);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clip_drawable, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        drawable = (ClipDrawable) binding.imageView.getDrawable();
        //定时器
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = IS_CONTUNUE;
                handler.sendMessage(msg);
                if (drawable.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        }, 0, 200);
    }
}
