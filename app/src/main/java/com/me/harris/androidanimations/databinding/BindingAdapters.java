package com.me.harris.androidanimations.databinding;

import android.content.Context;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by Harris on 2017/2/25.
 */

public class BindingAdapters {



//    https://medium.com/google-developers/android-data-binding-list-tricks-ef3d5630555e#.towfb0q40

    @BindingAdapter({"entries", "layout"})
    public static <T> void setEntries(ViewGroup viewGroup,
                                      List<T> entries, int layoutId) {
        viewGroup.removeAllViews();
        if (entries != null) {
            LayoutInflater inflater = (LayoutInflater)
                    viewGroup.getContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < entries.size(); i++) {
                T entry = entries.get(i);
                ViewDataBinding binding = DataBindingUtil
                        .inflate(inflater, layoutId, viewGroup, true);
//                binding.setVariable(BR.data, entry);
            }
        }
    }



}
