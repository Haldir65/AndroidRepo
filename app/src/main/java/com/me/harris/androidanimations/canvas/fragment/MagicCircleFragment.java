package com.me.harris.androidanimations.canvas.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentMagicCircleBinding;

/**
 * Created by Fermi on 2016/9/22.
 */

public class MagicCircleFragment extends Fragment {

    private FragmentMagicCircleBinding binding;

    public static MagicCircleFragment newInstance() {

        Bundle args = new Bundle();

        MagicCircleFragment fragment = new MagicCircleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_magic_circle, container, false);
        return binding.getRoot();
    }


}
