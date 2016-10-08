package com.me.harris.androidanimations._04_customView.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentSesameCreditV1Binding;

/**
 * Created by Fermi on 2016/9/23.
 */

public class SesameCreditFragment extends Fragment {

    FragmentSesameCreditV1Binding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sesame_credit_v1, container, false);
        return binding.getRoot();
    }
}
