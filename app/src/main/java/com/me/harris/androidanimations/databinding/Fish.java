package com.me.harris.androidanimations.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableLong;

/**
 * Created by Harris on 2017/2/25.
 */

public class Fish extends BaseObservable{


    String  name;
    ObservableLong id;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fish(String name, ObservableLong id) {
        this.name = name;
        this.id = id;
    }
}
