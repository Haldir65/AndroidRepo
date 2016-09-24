package com.me.harris.androidanimations.canvas.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.canvas.activity.CanvasActivity;
import com.me.harris.androidanimations.databinding.FragmentCanvasBinding;
import com.me.harris.androidanimations.interfaces.GenericCallBack;
import com.me.harris.androidanimations.ui.recyclerview.adapter.MainAdapter;
import com.me.harris.androidanimations.ui.recyclerview.itemDecoration.MainAdapterItemDecoration;
import com.me.harris.androidanimations.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2016/9/19.
 */

public class canvasMainFragment extends Fragment {
    FragmentCanvasBinding binding;
    private MainAdapter mAdapter;

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
                ((CanvasActivity) getActivity()).setToolBarTitle(fragmentClass.getSimpleName());

            }
        });
        List<Pair<String, Class>> mDatas = new ArrayList<>();
        mDatas.add(new Pair<String, Class>("QQHealthFragmentV1", QQHealthFragmentV1.class));
        mDatas.add(new Pair<String, Class>("QQHealthFragmentV2", QQHealthFragmentV2.class));
        mDatas.add(new Pair<String, Class>("AirConditionerViewFragment", AirConditionerViewFragment.class));
        mDatas.add(new Pair<String, Class>("SesameCreditFragment", SesameCreditFragment.class));
        mDatas.add(new Pair<String, Class>("ProgressBarViewFragment", ProgressBarViewFragment.class));
        mDatas.add(new Pair<String, Class>("LoadingLeafFragment", LoadingLeafFragment.class));
        mDatas.add(new Pair<String, Class>("Path_BezierFragment", Path_BezierFragment.class));
        mDatas.add(new Pair<String, Class>("MagicCircleFragment", MagicCircleFragment.class));
        mDatas.add(new Pair<String, Class>("ZHFollowButtonFragment", ZHFollowButtonFragment.class));
        mDatas.add(new Pair<String, Class>("CirclingArrowFragment", CirclingArrowFragment.class));
        mAdapter.setmDatas(mDatas);


    }

    public static canvasMainFragment newInstance() {

        Bundle args = new Bundle();

        canvasMainFragment fragment = new canvasMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.w("");
        ((CanvasActivity) getActivity()).setToolBarTitle(canvasMainFragment.class.getSimpleName());

    }


}
