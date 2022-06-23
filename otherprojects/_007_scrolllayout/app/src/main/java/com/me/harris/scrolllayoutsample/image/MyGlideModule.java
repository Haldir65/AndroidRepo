package com.me.harris.scrolllayoutsample.image;

import android.content.Context;
import android.os.Environment;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.me.harris.scrolllayoutsample.App;

import java.io.InputStream;

@GlideModule
public class MyGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        super.applyOptions(context, builder);

        //内存缓存相关,默认是24m
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));

//        作者：从心开始的我
//        链接：https://www.jianshu.com/p/ab97d6bda8ec
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //设置磁盘缓存及其路径
        //
        int MAX_CACHE_SIZE = 100 * 1024 * 1024;
        String CACHE_FILE_NAME = "imgCache";
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,CACHE_FILE_NAME,MAX_CACHE_SIZE));
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String downloadDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                    CACHE_FILE_NAME;
            //路径---->sdcard/imgCache
            builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, MAX_CACHE_SIZE));
        } else {
            //路径---->/sdcard/Android/data/<application package>/cache/imgCache
            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE));
        }

//        作者：从心开始的我
//        链接：https://www.jianshu.com/p/ab97d6bda8ec
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(App.client);
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);
//        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        //配置glide网络加载框架

    }


    @Override
    public boolean isManifestParsingEnabled() {
        //不使用清单配置的方式,减少初始化时间
        return false;
    }


}
