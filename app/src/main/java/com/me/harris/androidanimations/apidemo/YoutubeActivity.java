package com.me.harris.androidanimations.apidemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityYoutubeBinding;

/**
 * Created by Fermi on 2016/10/2.
 */

public class YoutubeActivity extends BaseAppCompatActivity {
    ActivityYoutubeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.header.setText(binding.listView.getAdapter().getItem(position).toString());
                binding.dragLayout.setVisibility(View.VISIBLE);
                binding.dragLayout.maximize();

            }
        });

        binding.listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public Object getItem(int position) {
                return "object "+position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                }
                ((TextView) view.findViewById(android.R.id.text1)).setText((CharSequence) getItem(position));
                return view;
            }
        });
    }
}
