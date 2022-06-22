package com.me.harris.androidanimations._43_ipc_provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.LogUtil;

/**
 * Created by Harris on 2018/3/23.
 */

public class ProviderActivity extends AppCompatActivity {

    public static final String TAG = ProviderActivity.class.getSimpleName();

    private TextView mTvShowBooks; // 显示书籍
    private TextView mTvShowUsers; // 显示用户

    Button mButton1;
    Button mButton2;
    Button mButton3;

    int index = 22;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_provider);
        mTvShowBooks = findViewById(R.id.tv1);
        mTvShowUsers = findViewById(R.id.tv2);


        mButton1 = findViewById(R.id.btn_1);
        mButton2 = findViewById(R.id.btn_2);
        mButton3 = findViewById(R.id.btn_3);

    }



    public void addBooks(View view){
        Uri bookUri = BookProvider.BOOK_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put("_id", index);
        values.put("name","Martin");
        getContentResolver().insert(bookUri, values);
        index++;
    }

    public void showBooks(View view){
        String content = "";
        Uri bookUri = BookProvider.BOOK_CONTENT_URI;
        String name = getContentResolver().getType(bookUri);
        LogUtil.p("================"+name);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        if (bookCursor != null) {
            while (bookCursor.moveToNext()) {
                Book book = new Book();
                book.bookId = bookCursor.getInt(0);
                book.bookName = bookCursor.getString(1);
                content += book.toString() + "\n";
                Log.e(TAG, "query book: " + book.toString());
                mTvShowBooks.setText(content);
            }
            bookCursor.close();
        }
    }

    public void showUsers(View view){
        String content = "";
        Uri userUri = BookProvider.USER_CONTENT_URI;
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                User user = new User();
                user.userId = userCursor.getInt(0);
                user.userName = userCursor.getString(1);
                user.isMale = userCursor.getInt(2) == 1;
                content += user.toString() + "\n";
                Log.e(TAG, "query user:" + user.toString());
                mTvShowUsers.setText(content);
            }
            userCursor.close();
        }
    }
}
