package com.me.harris.androidanimations.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**

 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    protected Context context;
    protected List<T> list = null;

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    protected final void setDataList(List<T> list) {
        this.list = list;
    }

    public void destroy() {
        context = null;
        if(list != null) {
            list.clear();
            list = null;
        }
    }

    public void updateDataList(List<T> list) {
        setDataList(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
    
    @Override
    public Object getItem(int position) {
        return list != null && position >= 0 && position < list.size() ? list
                .get(position) : null;
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder<T> holder;
        if (convertView == null) {
            convertView = createView(context, parent);
            holder = createViewHolder();
            holder.needInflate = false;
            convertView.setTag(holder);
            holder.init(context, convertView);
        }else if(((ViewHolder)convertView.getTag()).needInflate){
            convertView = createView(context, parent);
            holder = createViewHolder();
            holder.needInflate = false;
            convertView.setTag(holder);
            holder.init(context, convertView);
        }
        else {
            holder = (ViewHolder<T>) convertView.getTag();
        }
        T data = list.get(position);
        holder.position = position;
        holder.size = list.size();
        holder.update(context, data);
        return convertView;
    }
    
    public abstract View createView(Context context, ViewGroup parent);
    
    public abstract ViewHolder<T> createViewHolder();
    
    /**
     * 保存当前Item的view
     */
    public static abstract class ViewHolder<T> {
        public boolean needInflate;

        public int position;

        public int size;
        
        public abstract void init(Context context, View convertView);
        
        public abstract void update(Context context, T data);
    }


}
