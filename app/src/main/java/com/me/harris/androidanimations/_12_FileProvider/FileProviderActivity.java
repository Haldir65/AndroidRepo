package com.me.harris.androidanimations._12_FileProvider;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityFileProviderBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

import java.io.File;

import static android.support.v4.content.FileProvider.getUriForFile;

/**
 * Created by Harris on 2016/10/22.
 */

public class FileProviderActivity extends AppCompatActivity implements ActionCallBack {

    ActivityFileProviderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_file_provider);
        binding.setCallback(this);
        File imagePath = new File(getFilesDir(), "images");
        File newFile = new File(imagePath, "default_image.jpg");
        Uri contentUri = getUriForFile(this, "com.harris.me.fileprovider", newFile);
    }

    @Override
    public void onClickView(View view) {

    }
}
