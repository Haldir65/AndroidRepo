package com.me.harris.androidanimations.databinding;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableLong;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2017/2/25.
 */

public class FragmentListTricks extends Fragment {

    FragmentListTricksBinding binding;

    public static final String TAG = FragmentListTricks.class.getSimpleName();

    List<Fish> mList;

    public static FragmentListTricks newInstance() {
        Bundle args = new Bundle();
        FragmentListTricks fragment = new FragmentListTricks();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_tricks, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ObservableLong observableLong = new ObservableLong(i);
            Fish fish = new Fish(String.valueOf(i+1),observableLong);
            mList.add(fish);
        }
        binding.setEntries(mList);
    }
}
