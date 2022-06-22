package com.me.harris.androidanimations.customview.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentProgressViewBinding;

/**
 * Created by Fermi on 2016/9/23.
 */

public class ProgressBarViewFragment extends Fragment {
    FragmentProgressViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_progress_view, container, false);
        return binding.getRoot();
    }
}
