package com.me.harris.androidanimations._12_oom_example;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityOutOfMemoryBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;
import com.me.harris.androidanimations.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/** run on DalvikVM it will crash ,on art ,due to the special heap ,not crash
 * Created by Harris on 2016/10/24.
 */

public class OutOfMemoryActivity extends AppCompatActivity implements ActionCallBack {
    ActivityOutOfMemoryBinding binding;

    public static final String TAG = OutOfMemoryActivity.class.getSimpleName();
    private final List<Chunk> mRetainedChunks = new ArrayList<>();
    private final List<Chunk> mTempChunks = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_out_of_memory);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        Runtime runtime;
        switch (view.getId()) {
            case R.id.button1:
                int i = 0;
                runtime = Runtime.getRuntime();
                long max = runtime.maxMemory() - 1024 * 1024;
                while (runtime.totalMemory() < max) {
                    ((i++ % 2 == 0) ? mRetainedChunks : mTempChunks).add(new Chunk());
                }
                break;
            case R.id.button2:
                mTempChunks.clear();
                System.gc();
                break;
            case R.id.button3:
                runtime = Runtime.getRuntime();
                binding.textViewRam.setText("HeapMax = " + toMB(runtime.maxMemory()));
              /*  LogUtil.w(TAG, String.format("heap: %.2f/%.2f",
                        toMB(runtime.freeMemory()),
                        toMB(runtime.totalMemory())));*/
                LogUtil.w(TAG, String.valueOf(toMB(runtime.freeMemory())));
                LogUtil.w(TAG, String.valueOf(toMB(runtime.totalMemory())));
                break;
            case R.id.button4:
                Bitmap bitmap = Bitmap.createBitmap(1024, 512, Bitmap.Config.ARGB_8888);
                //about 2 MB size
                break;
            default:
                break;
        }
    }

    /**
     * size > 1MB
     */
    private static class Chunk {
        byte[] padding = new byte[1024 * 1024];
    }

    private String toMB(long number) {
        return String.format("%.2f", number / 1024.0 / 1024.0);
    }
}
