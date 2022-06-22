package com.me.harris.androidanimations._16_floatingBubble;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;

/**
 * Created by harris on 2016/11/22.
 */

public class FloatingBubbleActivity extends BaseAppCompatActivity {
    private FloatBubbleView mFloatingBubble;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_bubbleview);
        mFloatingBubble = (FloatBubbleView) findViewById(R.id.bubble);
        BubbleDrawer bubbleDrawer = new BubbleDrawer(this);
        // 设置渐变背景 如果不需要渐变 设置相同颜色即可
        bubbleDrawer.setBackgroundGradient(new int[] { 0xffffffff, 0xffffffff });
        // 给SurfaceView设置一个绘制者
        mFloatingBubble.setDrawer(bubbleDrawer);
    }

    @Override
    public void onSetStatusBarMode() {
        //do nothing here , full screen for those bubble !
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFloatingBubble.onDrawResume();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mFloatingBubble.onDrawPause();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFloatingBubble.onDrawDestroy();
    }
}
