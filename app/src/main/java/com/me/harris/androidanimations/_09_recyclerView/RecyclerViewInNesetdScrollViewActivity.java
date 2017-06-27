package com.me.harris.androidanimations._09_recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityNestedScrollRecyclerViewBinding;
import com.me.harris.androidanimations.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2016/12/24.
 */

public class RecyclerViewInNesetdScrollViewActivity extends BaseAppCompatActivity {

    ActivityNestedScrollRecyclerViewBinding binding;
    private PlainAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nested_scroll_recycler_view);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.recyclerView.setAdapter(mAdapter = new PlainAdapter());
        binding.recyclerView.addItemDecoration(new PlainItemDecoration(this));
    }

    private static class PlainAdapter extends RecyclerView.Adapter<PlainViewHolder> {

        public void setItems(List<Person> list) {
            // TODO: 2017/2/5 更新列表只需调用set函数，后续UI更新由DiffUtil完成
        }

        @Override
        public PlainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plain_card_view, parent, false);
            return new PlainViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PlainViewHolder holder, int position) {
            holder.textView.setText("Merry Christmas  " + position);
        }

        @Override
        public int getItemCount() {
            return 1000;
        }
    }

    private static class PlainViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PlainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    private static class PlainItemDecoration extends RecyclerView.ItemDecoration {
        private int offset;
        private Paint mPaint;

        public PlainItemDecoration(Context context) {
            offset = Utils.dip2px(context, 10);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(ContextCompat.getColor(context, R.color.md_white_1000));
            mPaint.setStyle(Paint.Style.FILL);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(offset*3, offset, offset*3, offset);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            for (int i = 0; i < parent.getChildCount(); i++) {
                final View child = parent.getChildAt(i);
                c.drawRect(
                        layoutManager.getDecoratedLeft(child) + offset,
                        layoutManager.getDecoratedTop(child) + offset,
                        layoutManager.getDecoratedRight(child) - offset,
                        layoutManager.getDecoratedBottom(child) - offset,
                        mPaint);
            }
        }
    }

    public void updateList(ArrayList<Person> oldList,ArrayList<Person> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldList, newList));
        mAdapter.setItems(newList);
        diffResult.dispatchUpdatesTo(mAdapter);//无需notifyDataSetChanged
    }



    private static class Person{
        int id;
    }

    public class MyDiffCallback extends DiffUtil.Callback{

        List<Person> oldPersons;
        List<Person> newPersons;

        public MyDiffCallback(List<Person> newPersons, List<Person> oldPersons) {
            this.newPersons = newPersons;
            this.oldPersons = oldPersons;
        }

        @Override
        public int getOldListSize() {
            return oldPersons.size();
        }

        @Override
        public int getNewListSize() {
            return newPersons.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPersons.get(oldItemPosition).id == newPersons.get(newItemPosition).id;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
