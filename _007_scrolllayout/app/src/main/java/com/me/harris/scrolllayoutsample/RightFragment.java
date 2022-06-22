package com.me.harris.scrolllayoutsample;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightFragment extends Fragment implements ScrollableHelper.ScrollableContainer {


    RecyclerView mRecyclerView;
    
    public static RightFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RightFragment fragment = new RightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ListAapter mAdapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new ListAapter();
        mAdapter.picRes = R.drawable.image_41;
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.addItemDecoration(new ListDecoration());

    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }
}
