package com.me.harris.androidanimations.touch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Harris on 2016/10/6.
 */

public class SwipeLayoutFragment extends Fragment {

    public static SwipeLayoutFragment newInstance() {
        Bundle args = new Bundle();
        SwipeLayoutFragment fragment = new SwipeLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
