package com.me.harris.androidanimations._20_annotations;

import com.example.PojoString;


/**
 * Created by Harris on 2017/1/1.
 */

@PojoString
public class StringPojo {
    public String s1;
    public String s2;

    public StringPojo(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public String toString() {
       return StringUtil2.createString(this); //StringUtil是在编译期自动生成的，编译一下就过去了
    }
}
