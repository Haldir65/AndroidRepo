package com.me.harris.androidanimations._06_touch.swipelistview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentSwipeListviewBinding;
import com.me.harris.androidanimations.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2017/4/5.
 */

public class SwipeListViewFragment extends Fragment {
    FragmentSwipeListviewBinding binding;

    SwipeAdapter mAdapter;
    List<String> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe_listview, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add("值" + i);
        }
        mAdapter = new SwipeAdapter(getActivity(), mList);
        binding.swipeListView.setAdapter(mAdapter);

    }

    static class SwipeAdapter extends BaseAdapter<String> {

        public SwipeAdapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        public View createView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_sticky_swipe_listview, parent, false);
        }

        @Override
        public ViewHolder<String> createViewHolder() {
            return new SwipeViewHolder();
        }
    }

    static class SwipeViewHolder extends BaseAdapter.ViewHolder<String> {
        LinearLayout mLlRootView;
        ImageView mIvTopView;
        LinearLayout mLlBackView;

        @Override
        public void init(Context context, View convertView) {
            mLlRootView = (LinearLayout) convertView.findViewById(R.id.ll_rootView);
            mIvTopView = (ImageView) convertView.findViewById(R.id.iv_top_view);
            mLlBackView = (LinearLayout) convertView.findViewById(R.id.ll_back_view);
        }

        @Override
        public void update(Context context, String data) {
        }
    }
}
