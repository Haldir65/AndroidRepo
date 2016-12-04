package com.me.harris.androidanimations.interfaces;

import android.widget.CompoundButton;

/**
 * Created by Fermi on 2016/12/4.
 * CheckBox的onCheckChangedListener
 */

public interface onCheckChangedCallback {
    public void completeChanged(CompoundButton compoundButton, boolean checked);
}
