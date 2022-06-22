package com.me.harris.androidanimations.customview.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmntShapeButtonBinding;

/**
 * Created by Harris on 2017/9/13.
 */

public class ShapeButtonFragment extends Fragment {

    FragmntShapeButtonBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmnt_shape_button, container, false);
        return binding.getRoot();
    }
}
