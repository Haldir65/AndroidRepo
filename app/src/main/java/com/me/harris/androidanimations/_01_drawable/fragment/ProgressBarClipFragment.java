package com.me.harris.androidanimations._01_drawable.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentClipProgressbarBinding;

/**
 * Created by Fermi on 2016/10/8.
 */

public class ProgressBarClipFragment extends Fragment {
    FragmentClipProgressbarBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clip_progressbar, container, false);
        return binding.getRoot();
    }
}
