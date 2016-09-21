package com.me.harris.androidanimations.interfaces;

import android.view.View;

/**
 * Created by Fermi on 2016/9/21.
 */

public interface GenericCallBack<T> {
    void onClick(View view, T t);
}
