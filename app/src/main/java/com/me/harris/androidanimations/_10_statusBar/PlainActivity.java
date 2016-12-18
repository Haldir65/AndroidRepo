package com.me.harris.androidanimations._10_statusBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.ToastUtils;
import com.me.harris.androidanimations.utils.Utils;


/** for fun ,用来观察setFitSystemWindows的问题
 * Created by Harris on 2016/12/18.
 */

public class PlainActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView mScrollView;
    private LinearLayout mLinearLayout;
    private ImageView mImage;
    private RelativeLayout mRelativeLayout;
    private Button mButton1;
    private Button mButton2;

    private boolean flag = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain);


        mScrollView = (ScrollView) findViewById(R.id.ScrollView);
        mLinearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
        mImage = (ImageView) findViewById(R.id.image);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);

        ViewCompat.setFitsSystemWindows(mScrollView,false);
        mImage.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//这句话去掉黑色阴影
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                flag = !flag;
                ViewCompat.setFitsSystemWindows(mScrollView, flag);
                ToastUtils.showTextShort(this, "当前" + (flag ? "设置了fitSystemWindows" : ""));
                break;
            case R.id.button2:
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mScrollView.getLayoutParams();
                if (mScrollView.getTop() == Utils.getStatusBarHeight() && params.topMargin == Utils.getStatusBarHeight()) {
                    params.setMargins(params.leftMargin, 0, params.rightMargin, params.bottomMargin);
                    mScrollView.setLayoutParams(params);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//这句话去掉黑色阴影
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));

                    ToastUtils.showTextShort(this,"进入全屏模式");
                } else {
                    params.setMargins(params.leftMargin, Utils.getStatusBarHeight(), params.rightMargin, params.bottomMargin);
                    mScrollView.setLayoutParams(params);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//这句话去掉黑色阴影
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_blue_800));

                    ToastUtils.showTextShort(this,"进入着色模式");
                }
                break;
            case R.id.image:
                break;
            default:
        }

    }
}
