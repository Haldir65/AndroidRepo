package com.me.harris.androidanimations.customview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentCirclingArrowBinding;

/**
 * Created by Fermi on 2016/9/24.
 */

public class CirclingArrowFragment extends Fragment {
    FragmentCirclingArrowBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_circling_arrow, container, false);
        return binding.getRoot();
    }
}
