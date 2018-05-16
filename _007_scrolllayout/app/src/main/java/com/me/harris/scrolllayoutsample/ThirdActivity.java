package com.me.harris.scrolllayoutsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.me.harris.scrolllayoutsample.image.GlideApp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImage1,mImage2,mImage3,mImage4,mImage5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mImage1 = findViewById(R.id.image1);
        mImage2 = findViewById(R.id.image2);
        mImage3 = findViewById(R.id.image3);
        mImage4 = findViewById(R.id.image4);
        mImage5 = findViewById(R.id.image5);
        client = App.client;
        addListeners();
        startLoad();
    }

    private void addListeners() {
        mImage1.setOnClickListener(this);
        mImage2.setOnClickListener(this);
        mImage3.setOnClickListener(this);
    }

    public static final String qiniuImageUrl = "http://h.hiphotos.baidu.com/image/pic/item/08f790529822720e23a3d3b777cb0a46f21fab09.jpg";

    private void startLoad() {
        GlideApp.with(this)
                .load(qiniuImageUrl)
                .into(mImage1);
//        Glide.with(this)
//                .load(R.drawable.image_44)
//                .into(mImage2);
//        Glide.with(this)
//                .load(R.drawable.img_01)
//                .into(mImage3);
//        Glide.with(this)
//                .load(R.drawable.img_03)
//                .into(mImage4);
//        Glide.with(this)
//                .clear(mImage1);
//        Glide.with(this)
//                .load(R.drawable.image_78)
//                .into(mImage5);
        GlideApp.with(this)
                .asBitmap()
                .load(qiniuImageUrl)
                .placeholder(R.drawable.img_02)
                .centerCrop()
                .into(mImage5);

    }

    OkHttpClient client;
    public static final String TAG = ThirdActivity.class.getSimpleName();
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
                Request request = new Request.Builder()
                        .url("http://gank.io/api/data/Android/10/1")
                        .get()
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, response.body().string());
                    }
                });
                break;
            case R.id.image2:
                break;
            case R.id.image3:
                break;
                default:
                    break;
        }
    }
}
