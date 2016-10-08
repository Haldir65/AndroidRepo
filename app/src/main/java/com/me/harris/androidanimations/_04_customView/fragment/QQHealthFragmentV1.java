package com.me.harris.androidanimations._04_customView.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentQqhealthV1Binding;

/**
 * Created by Fermi on 2016/9/23.
 */

public class QQHealthFragmentV1 extends Fragment {

    FragmentQqhealthV1Binding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qqhealth_v1, container, false);
        return binding.getRoot();
    }
}
