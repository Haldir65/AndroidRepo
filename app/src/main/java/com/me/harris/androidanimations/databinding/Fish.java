package com.me.harris.androidanimations.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableLong;

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
