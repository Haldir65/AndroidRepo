package com.me.harris.androidanimations._18_animating_binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Fermi on 2016/12/4.
 */

public class GirlsAlpha extends BaseObservable {

    @Bindable
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
