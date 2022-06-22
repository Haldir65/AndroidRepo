package com.me.john;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

import com.mark.library.PluginInterface;

public class MainActivity extends FragmentActivity implements PluginInterface {
    FragmentActivity mHostContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void setContentView(int layoutResID) {
        mHostContext.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        mHostContext.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mHostContext.setContentView(view, params);
    }



    @Override
    public Window getWindow() {
        return mHostContext.getWindow();
    }



    @Override
    public void attachContext(FragmentActivity context) {
        mHostContext = context;
    }



    @Override
    public LayoutInflater getLayoutInflater() {
        return mHostContext.getLayoutInflater();
    }


    @Override
    public View findViewById(int id) {
        return mHostContext.findViewById(id);
    }


    @Override
    public ClassLoader getClassLoader() {
        return mHostContext.getClassLoader();
    }

    @Override
    public WindowManager getWindowManager() {
        return mHostContext.getWindowManager();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void onBackPressed() {
        mHostContext.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
        mHostContext.startActivity(intent);
    }


    @Override
    public ApplicationInfo getApplicationInfo() {
        return mHostContext.getApplicationInfo();
    }

    @Override
    public void finish() {
        mHostContext.finish();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
