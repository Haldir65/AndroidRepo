package com.me.harris.androidanimations.touch.fragment;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentSwipeLayoutBinding;
import com.me.harris.androidanimations.databinding.ItemSwipeListViewBinding;
import com.me.harris.androidanimations.touch.widget.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2016/10/6.
 */

public class SwipeLayoutFragment extends Fragment {
    FragmentSwipeLayoutBinding binding;
    List<String> nums;
    MyAdapter adapter;

    public static SwipeLayoutFragment newInstance() {
        Bundle args = new Bundle();
        SwipeLayoutFragment fragment = new SwipeLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe_layout, container, false);
        return binding.getRoot();
    }

    private void InitData() {
        nums = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            nums.add(i + "");
        }
        adapter = new MyAdapter(getActivity());
        binding.listView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        InitData();
    }
    private class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            super();
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return nums.size();
        }

        @Override
        public Object getItem(int position) {
            return nums.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_swipe_list_view, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

                /**
                 * 添加swipelayout
                 */
                SwipeLayout.addSwipeView(holder.binding.swipelayout);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.binding.tvName.setText(nums.get(position));
            holder.binding.llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nums.remove(position);
                    notifyDataSetChanged();

                    /**
                     * 删除swipelayout
                     */
                    SwipeLayout.removeSwipeView(holder.binding.swipelayout);

                }
            });
            return convertView;
        }
    }

    class ViewHolder {
        ItemSwipeListViewBinding binding;
      /*  TextView tvName;
        LinearLayout llEdit;
        LinearLayout llDelete;
        SwipeLayout swipelayout;*/

        ViewHolder(View view) {
            binding = DataBindingUtil.bind(view);
        }
    }
}
