package com.me.harris.androidanimations.animatingbinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Fermi on 2016/12/4.
 */

public class GirlsAlpha extends BaseObservable {

    @Bindable
    private boolean visible;

    @Bindable
    private int visibility;


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
