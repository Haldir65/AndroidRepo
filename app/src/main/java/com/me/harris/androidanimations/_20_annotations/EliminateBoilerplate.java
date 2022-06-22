package com.me.harris.androidanimations._20_annotations;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.me.harris.androidanimations.R;

/** 演示AnnotationProcessor的基本使用，主要用于 避免手动写过多的重复性代码，在编译时生成新的java文件
 * Created by Harris on 2016/12/31.
 */

public class EliminateBoilerplate extends AppCompatActivity  {

    private Toolbar mToolbar;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        mTextView = (TextView) findViewById(R.id.textView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButton = (Button) findViewById(R.id.button);
        setSupportActionBar(mToolbar);
        mTextView.setText(new StringPojo("haha","dsdsd").toString());

    }


}
