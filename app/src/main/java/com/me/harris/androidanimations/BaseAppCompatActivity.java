package com.me.harris.androidanimations;

import android.graphics.Color;
import android.os.Build;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.me.harris.androidanimations.utils.Utils;

/**
 * 把一些常规的方法丢到这里
 * Created by harris on 2016/11/15.
 */

public class BaseAppCompatActivity extends AppCompatActivity {

    private View mStatusBarView;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            onSetStatusBarMode();
        }
    }

    /**
     * 在setContentView之后会被调用，子类可以通过复写的方式决定具体实现
     *
     * @param
     */
    public void onSetStatusBarMode() {
            setStatusBarColor(R.color.colorPrimaryDark);
    }

    protected void handleFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected void handleTintMode() {
        //使用着色模式
        setStatusBarColor(R.color.colorPrimaryDark);
//        moveToolbarDownwards();
    }

    public void moveContentViewDownWards() {
        ViewGroup contentview = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (contentview != null) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) contentview.getLayoutParams();
            params.setMargins(params.leftMargin, Utils.getStatusBarHeight(), params.rightMargin, params.bottomMargin);
            contentview.setLayoutParams(params);
        }
    }

    public void moveContentViewUpwards() {
        ViewGroup contentView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (contentView != null) {
            if (contentView.getTop()==Utils.getStatusBarHeight()) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) contentView.getLayoutParams();
                if (params.topMargin == Utils.getStatusBarHeight()) {
                    params.setMargins(params.leftMargin, 0, params.rightMargin, params.bottomMargin);
                    contentView.setLayoutParams(params);
                }
            }
        }
    }

    /**
     * 对外提供的设置statusBar颜色的方法,子类请不要复写该方法
     *
     * @param statusBarColor
     */
    public void setStatusBarColor(@ColorRes int statusBarColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//这句话去掉黑色阴影
            getWindow().setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            if (mStatusBarView == null) {
                mStatusBarView = new View(this);
                mStatusBarView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getStatusBarHeight()));
                mStatusBarView.setBackgroundColor(ContextCompat.getColor(this, statusBarColor));
                decorView.addView(mStatusBarView);
            } else {
                mStatusBarView.setBackgroundColor(ContextCompat.getColor(this, statusBarColor));
            }
        }
    }

    public void setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//这句话去掉黑色阴影
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (mStatusBarView != null) {
                mStatusBarView.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
            }
        }
    }


    public void setDarkStatusIcon(boolean bDark) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            View decorView = getWindow().getDecorView();
            if(decorView != null){
                int vis = decorView.getSystemUiVisibility();
                if(bDark){
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else{
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

}
