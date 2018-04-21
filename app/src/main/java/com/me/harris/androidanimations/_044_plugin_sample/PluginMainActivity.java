package com.me.harris.androidanimations._044_plugin_sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.LogUtil;
import com.me.harris.androidanimations.utils.ToastUtils;

import java.io.File;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PluginMainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String APK_NAME = "signed.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        findViewById(R.id.button1).setOnClickListener(this);
        PluginManager.getInstance().setContext(this);
        Completable.fromRunnable(() -> PluginManager.getInstance().loadApk(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + APK_NAME)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        ToastUtils.showTextShort(PluginMainActivity.this, "插件加载完毕");
                        LogUtil.w("插件加载完毕");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    public void startApk(View view) {
        Intent intent = new Intent(this, PluginHostActivity.class);
        String otherApkMainActivityName = PluginManager.getInstance().getPluginPackageArchiveInfo().activities[0].name;
        intent.putExtra("className", otherApkMainActivityName);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startApk(v);
                break;
            default:
                break;
        }
    }
}
