package com.me.harris.androidanimations._09_recyclerView.adapter;

import androidx.annotation.ColorRes;

/**
 * Created by Harris on 2017/8/16.
 */

public class Person {
    int id;
    String text;

    @ColorRes
   public int textColor;

    public Person(String text) {
        this.text = text;
    }

    public Person(String text, int textColor) {
        this.text = text;
        this.textColor = textColor;
    }
}
