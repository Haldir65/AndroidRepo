package com.me.harris.androidanimations.customview.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentQqhealthV2Binding;

/**
 * Created by Fermi on 2016/9/23.
 */

public class QQHealthFragmentV2 extends Fragment {
    FragmentQqhealthV2Binding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qqhealth_v2, container, false);
        return binding.getRoot();
    }
}
