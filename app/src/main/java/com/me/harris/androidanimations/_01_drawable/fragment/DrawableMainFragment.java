package com.me.harris.androidanimations._01_drawable.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._01_drawable.activity.DrawableMainActivity;
import com.me.harris.androidanimations.databinding.FragmentCanvasBinding;
import com.me.harris.androidanimations.interfaces.GenericCallBack;
import com.me.harris.androidanimations._09_recyclerView.adapter.MainAdapter;
import com.me.harris.androidanimations._09_recyclerView.itemDecoration.MainAdapterItemDecoration;
import com.me.harris.androidanimations.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermi on 2016/10/8.
 */

public class DrawableMainFragment extends Fragment {
    FragmentCanvasBinding binding;

    private MainAdapter mAdapter;

    public static DrawableMainFragment newInstance() {
        Bundle args = new Bundle();
        DrawableMainFragment fragment = new DrawableMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_canvas, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerView.addItemDecoration(new MainAdapterItemDecoration(getActivity()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainAdapter<>(R.layout.item_main, new GenericCallBack<Pair<String, Class<Fragment>>>() {
            @Override
            public void onClick(View view, Pair<String, Class<Fragment>> stringClassPair) {
                Class<Fragment> fragmentClass = stringClassPair.second;
                Fragment fragment = null;
                try {
                    fragment = fragmentClass.newInstance();//调用无参数的、私有构造函数
                } catch (java.lang.InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                getFragmentManager().beginTransaction()
                        .addToBackStack(fragmentClass.getSimpleName()).
                        replace(R.id.frameLayout, fragment).commit();
                ((DrawableMainActivity) getActivity()).setToolBarTitle(fragmentClass.getSimpleName());
            }
        });
        List<Pair<String, Class>> mDatas = new ArrayList<>();
        mDatas.add(new Pair<String, Class>("BitmapDrawable", BitmapDrawableFragmEnt.class));
        mDatas.add(new Pair<String, Class>("TransitionDrawable", TransitionDrawableFragment.class));
        mDatas.add(new Pair<String, Class>("ClipDrawable", ClipDrawableFragment.class));
        mDatas.add(new Pair<String, Class>("ProgressBar", ProgressBarClipFragment.class));
        mDatas.add(new Pair<String, Class>("RainBow", GradientDrawableFragment.class));
        mAdapter.setDatas(mDatas);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.w("");
    }
}
