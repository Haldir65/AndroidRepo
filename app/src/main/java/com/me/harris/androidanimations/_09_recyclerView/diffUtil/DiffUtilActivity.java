package com.me.harris.androidanimations._09_recyclerView.diffUtil;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityDiffUtilBinding;

/**
 * Created by Harris on 2017/2/12.
 */

public class DiffUtilActivity extends BaseAppCompatActivity {
    ActivityDiffUtilBinding binding;
    ActorAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diff_util);
        setSupportActionBar(binding.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAdapter = new ActorAdapter(ActorRepository.getActorListSortedByRating());
        binding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.sort_by_name) {
            mAdapter.swapItems(ActorRepository.getActorListSortedByName());
            return true;
        } else if (itemId == R.id.sort_by_rating) {
            mAdapter.swapItems(ActorRepository.getActorListSortedByRating());
            return true;
        } else if (itemId == R.id.sort_by_birth) {
            mAdapter.swapItems(ActorRepository.getActorListSortedByYearOfBirth());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
