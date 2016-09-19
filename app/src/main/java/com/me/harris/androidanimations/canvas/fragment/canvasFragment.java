package com.me.harris.androidanimations.canvas.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;

/**
 * Created by Harris on 2016/9/19.
 */

public class canvasFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_canvas, container, false);
    }

    public static canvasFragment newInstance() {

        Bundle args = new Bundle();

        canvasFragment fragment = new canvasFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
