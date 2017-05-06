package com.me.harris.androidanimations._34_bottomsheet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.harris.androidanimations.R;

import java.util.List;

/**
 * Created by Harris on 2017/5/6.
 */

public class BottomSheetItemAdapter extends RecyclerView.Adapter<BottomSheetItemAdapter.ViewHolder> {

    private List<BottomSheetItem> mBottomSheetItems;
    private BottomSheetItemListener mListener;

    public BottomSheetItemAdapter(List<BottomSheetItem> bottomSheetItems, BottomSheetItemListener listener) {
        mBottomSheetItems = bottomSheetItems;
        mListener = listener;
    }

    public void setListener(BottomSheetItemListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_sheet_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mBottomSheetItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mBottomSheetItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView textView;
        public BottomSheetItem bottomSheetItem;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void setData(BottomSheetItem bottomSheetItem) {
            this.bottomSheetItem = bottomSheetItem;
            imageView.setImageResource(bottomSheetItem.getDrawableResource());
            textView.setText(bottomSheetItem.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(bottomSheetItem);
            }
        }
    }

    public interface BottomSheetItemListener {
        void onItemClick(BottomSheetItem bottomSheetItem);
    }
}
