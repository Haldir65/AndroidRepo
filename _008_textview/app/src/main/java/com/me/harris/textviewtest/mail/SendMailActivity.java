package com.me.harris.textviewtest.mail;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.me.harris.textviewtest.R;

public class SendMailActivity extends AppCompatActivity {

    private EditText editText;
    private Button mButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        editText = findViewById(R.id.editText);
        mButton = findViewById(R.id.btn_1);
        mButton.setOnClickListener((v) -> {
            String content = editText.getText().toString();
            if (!TextUtils.isEmpty(content)){
                SendMailUtil.send(content);
            }
        });
    }
}
