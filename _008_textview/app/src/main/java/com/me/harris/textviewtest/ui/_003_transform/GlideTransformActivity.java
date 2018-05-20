package com.me.harris.textviewtest.ui._003_transform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.me.harris.textviewtest.R;

public class GlideTransformActivity extends AppCompatActivity {

    RoundCornerImageView mImageView;
    ImageView mOriginalImage;
    RoundCornerImageView mImageView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transform);
        mImageView = findViewById(R.id.image2);
        mOriginalImage = findViewById(R.id.image1);
        mImageView3 = findViewById(R.id.image3);
        mImageView.load("http://b.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d9dedd4b0389b033b5ab5b988.jpg");
        Glide.with(this)
                .load("http://b.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d9dedd4b0389b033b5ab5b988.jpg")
                .into(mOriginalImage);
        mImageView3.load2("http://b.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d9dedd4b0389b033b5ab5b988.jpg");

    }
}
