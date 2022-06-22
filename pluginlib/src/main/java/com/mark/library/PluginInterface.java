package com.mark.library;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public interface PluginInterface {
     void onCreate(Bundle saveInstance);
    void attachContext(FragmentActivity context);


    void onStart();

    void onResume();

    void onRestart();

    void onDestroy();

    void onStop();

    void onPause();

}
