package com.me.harris.ffmpegintegration;

import android.os.Environment;
import android.support.annotation.RequiresPermission;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class VideoUtil {
    @RequiresPermission(READ_EXTERNAL_STORAGE)
    @Nullable
    public static List<String> getVideoFileAbsolutePathList() {
        List<String> result = null;
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+Environment.DIRECTORY_MOVIES);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                result = new ArrayList<>(files.length);
                for (int i = 0; i < files.length; i++) {
                    result.add(files[i].getAbsolutePath());
                }
            }
        }
        return result;
    }
}
