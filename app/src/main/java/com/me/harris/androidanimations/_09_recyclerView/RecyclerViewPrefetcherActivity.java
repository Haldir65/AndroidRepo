package com.me.harris.androidanimations._09_recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityRecyclerViewPrefetcherBinding;
import com.me.harris.androidanimations.utils.Utils;

import java.util.Random;

/**
 * RecyclerView setInitialItemPrefetchCount
 * https://medium.com/google-developers/recyclerview-prefetch-c2f269075710#.ombuchbs5
 */

public class RecyclerViewPrefetcherActivity extends AppCompatActivity {

    ActivityRecyclerViewPrefetcherBinding binding;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view_prefetcher);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.recyclerView.setAdapter(new PrefetcherAdapter());
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setInitialPrefetchItemCount(10);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addItemDecoration(new PlainItemDecoration(this));
    }


    private static class PrefetcherAdapter extends RecyclerView.Adapter<PlainViewHolder> {

        private Context mContext;

        private int[] Ids = new int[]{R.drawable.alamby, R.drawable.image_01, R.drawable.image_02, R.drawable.image_03, R.drawable.image_04,
                R.drawable.image_05, R.drawable.image_06, R.drawable.image_07, R.drawable.image_08, R.drawable.image_09, R.drawable.image_10,
                R.drawable.image_11, R.drawable.image_12, R.drawable.image_13, R.drawable.image_14, R.drawable.image_15,
                R.drawable.image_16,R.drawable.image_17,R.drawable.image_18};
        private Random random;

        @DrawableRes
        private int generateRandomPicId() {
            random = new Random();
            int length = Ids.length;
            return Ids[random.nextInt(length)];
        }
        @Override
        public PlainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prefetcher_card_view, parent, false);
            return new PlainViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PlainViewHolder holder, int position) {
            holder.textView.setText("" + position);
            if (mContext == null) {
                mContext = holder.itemView.getContext();
            }
            Glide.with(mContext).load(generateRandomPicId()).diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(holder.iv_left);
            Glide.with(mContext).load(generateRandomPicId()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.iv_right);
        }

        @Override
        public int getItemCount() {
            return 1000;
        }
    }


    private static class PlainViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView iv_left;
        ImageView iv_right;

        public PlainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            iv_left = (ImageView) itemView.findViewById(R.id.iv_left);
            iv_right = (ImageView) itemView.findViewById(R.id.iv_right);
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

}
