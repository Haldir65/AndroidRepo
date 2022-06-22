package com.me.harris.androidanimations.customview.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentLoadingLeafBinding;

/**
 * Created by Fermi on 2016/9/23.
 */

public class LoadingLeafFragment extends Fragment {
    FragmentLoadingLeafBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loading_leaf, container, false);
        return binding.getRoot();
    }

}
