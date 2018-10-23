package com.me.harris.androidanimations.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.me.harris.androidanimations.R;

import java.util.ArrayList;

/**
 * Created by whuthm on 2016/1/20.
 */
public class PickerView extends ListView {
    
    private static final String TAG = "PickerView";
    
    private OnItemSelectedListener mItemSelectedListener;
    private OnItemClickListener mItemClickListener;
    private OnScrollListener mScrollListener;
    
    private OnPickerSelectedListener mPickerSelectedListener;
    
    private int mSelectorIndex;
    private int mSelectorCount;
    private final int mFixedWidth;
    private final int mFixedHeight;
    
    private int mPenddingCurrentItem;
    private int mCurrentItem = -1;
    
    private int mVisibleItemCount;
    
    private static final int MIN_DURATION = 300;
    private static final int MAX_DURATION = 1000;
    
    private Drawable mSelectorForeground;
    
    private Shader mShader;
    private Paint mPaint;
    private Matrix mMatrix;
    
    private Rect mTempRect = new Rect();
    
    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFootViews = new ArrayList<>();
    
    public PickerView(Context context) {
        this(context, null);
    }
    
    public PickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public PickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        setWillNotDraw(false);
        setDividerHeight(0);
        setOverScrollMode(OVER_SCROLL_NEVER);
        
        final Resources res = getResources();
        
        final TypedArray a = context
                .obtainStyledAttributes(attrs, R.styleable.PickerView);
        mFixedWidth = a.getDimensionPixelSize(R.styleable.PickerView_fixedWidth,
                res.getDimensionPixelSize(R.dimen.picker_item_default_fixed_width));
        mFixedHeight = a.getDimensionPixelSize(R.styleable.PickerView_fixedHeight,
                res.getDimensionPixelSize(R.dimen.picker_item_default_fixed_height));
        mSelectorCount = a.getInteger(R.styleable.PickerView_selectorCount, 3);
        mSelectorIndex = a.getInteger(R.styleable.PickerView_selectorIndex, 1);
        
        mSelectorForeground = a.getDrawable(R.styleable.PickerView_selectorForeground);
        if (mSelectorForeground == null) {
            mSelectorForeground = res.getDrawable(R.drawable.picker_selector_foreground);
        }
        
        if (mSelectorCount <= 0) {
            throw new RuntimeException("mSelectorCount <= 0");
        }
        
        if (mSelectorIndex < 0) {
            throw new RuntimeException("mSelectorIndex < 0");
        }
        
        if (mSelectorIndex >= mSelectorCount) {
            throw new RuntimeException("mSelectorIndex >= mSelectorCount");
        }
        
        if (mFixedWidth <= 0 || mFixedHeight <= 0) {
            throw new RuntimeException("mFixedWidth <=0 || mFixedHeight <= 0");
        }

        setSelectorConfig(mSelectorCount, mSelectorIndex);
        a.recycle();
        
        setOnItemSelectedListenerInternal(mItemSelectedListenerInternal);
        setOnItemClickListenerInternal(mItemClickListenerInternal);
        setOnScrollListenerInternal(mScrollListenerInternal);
        
        // setAdapter(new PickerAdapter());
        
        mMatrix = new Matrix();
        mPaint = new Paint();
        mShader = new LinearGradient(0, 0, 0, 1, 0xbb000000, 0x66000000,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        
        setSelector(R.drawable.picker_item_selector);
    }
    
    public PickerView setSelectorConfig(int count, int idx) {
        
        if (idx >= count) {
            throw new RuntimeException("mSelectorIndex >= mSelectorCount");
        }
        
        for (View v : mHeaderViews) {
            removeHeaderView(v);
        }
        mHeaderViews.clear();
        
        for (View v : mFootViews) {
            removeFooterView(v);
        }
        mFootViews.clear();
        
        mSelectorCount = count;
        mSelectorIndex = idx;
        
        for (int i = 0; i < mSelectorCount - 1; i++) {
            if (i < mSelectorIndex) {
                View v = new View(getContext());
                mHeaderViews.add(v);
                addHeaderView(v, null, false);
            }
            else {
                View v = new View(getContext());
                mFootViews.add(v);
                addFooterView(v, null, false);
            }
        }
        return this;
    }
    
    public void notifyDataChange() {
        ListAdapter adapter = getAdapter();
        BaseAdapter baseAdapter = null;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            if (headerViewListAdapter.getWrappedAdapter() instanceof BaseAdapter) {
                baseAdapter = (BaseAdapter) headerViewListAdapter.getWrappedAdapter();
            }
        }
        else if (adapter instanceof BaseAdapter) {
            baseAdapter = (BaseAdapter) adapter;
        }
        if (baseAdapter != null) {
            baseAdapter.notifyDataSetChanged();
        }
    }
    
    public void setCurrentItem(int currentItem) {
        setCurrentItem(currentItem, true);
    }
    
    public void setCurrentItem(int currentItem, boolean pendding) {
        if (pendding) {
            setPenddingCurrentItem(currentItem);
        }
        setCurrentItemInternal(currentItem);
    }
    
    private void setCurrentItemInternal(int currentItem) {
        int distance = getFirstVisibleViewTop() + mFixedHeight
                * (currentItem - getFirstVisiblePosition());
        smoothScrollBy(distance, mPenddingCurrentItem >= 0 ? MAX_DURATION : MIN_DURATION);
    }
    
    private void setPenddingCurrentItem(int currentItem) {
        mPenddingCurrentItem = currentItem;
    }
    
    public int getCurrentItem() {
        int childCount = getChildCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.getHitRect(mTempRect);
            if (mTempRect.contains(getScrollX(), getScrollY() + mSelectorIndex
                    * mFixedHeight)) {
                return firstVisiblePosition + i - mSelectorIndex;
            }
        }
        return firstVisiblePosition;
    }
    
    public int getPickerCount() {
        return getAdapter() != null ? getAdapter().getCount() - getHeaderViewsCount()
                - getFooterViewsCount() : 0;
    }
    
    private void ensureScrollWheelAdjusted() {
        View firstVisibleView = getFirstVisibleView();
        if (firstVisibleView != null) {
            int top = firstVisibleView.getTop();
            int absTop = Math.abs(top);
            int distance = 0;
            if (top < 0) {
                if (absTop > mFixedHeight / 2) {
                    distance = mFixedHeight + top;
                }
                else {
                    distance = top;
                }
            }
            smoothScrollBy(distance, MIN_DURATION);
        }
    }
    
    private View getFirstVisibleView() {
        final int childCount = getChildCount();
        if (childCount > 0) {
            View firstVisibleView = getChildAt(0);
            return firstVisibleView;
        }
        return null;
    }
    
    private int getFirstVisibleViewTop() {
        final int childCount = getChildCount();
        if (childCount > 0) {
            View firstVisibleView = getChildAt(0);
            return firstVisibleView.getTop();
        }
        return 0;
    }
    
    public void setOnPickerSelectedListener(OnPickerSelectedListener l) {
        mPickerSelectedListener = l;
    }
    
    private void onPickerSelectedChange(int position) {
        if (mCurrentItem == position) {
            return;
        }
        mCurrentItem = position;
        if (mPickerSelectedListener != null) {
            mPickerSelectedListener.onPickerSelected(position);
        }
    }
    
    private OnItemSelectedListener mItemSelectedListenerInternal = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (mItemSelectedListener != null) {
                mItemSelectedListener.onItemSelected(parent, view, position, id);
            }
        }
        
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            if (mItemSelectedListener != null) {
                mItemSelectedListener.onNothingSelected(parent);
            }
        }
    };
    
    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mItemSelectedListener = listener;
    }
    
    private void setOnItemSelectedListenerInternal(OnItemSelectedListener listener) {
        super.setOnItemSelectedListener(listener);
    }
    
    private OnItemClickListener mItemClickListenerInternal = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setCurrentItemInternal(position - getHeaderViewsCount());
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(parent, view, position, id);
            }
        }
    };
    
    @Override
    public void setOnItemClickListener(OnItemClickListener l) {
        mItemClickListener = l;
    }
    
    private void setOnItemClickListenerInternal(OnItemClickListener l) {
        super.setOnItemClickListener(l);
    }
    
    private OnScrollListener mScrollListenerInternal = new OnScrollListener() {
        
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            Log.i(TAG, "onScrollStateChanged scrollState = " + scrollState);
                onPickerSelectedChange(getCurrentItem());
            /** 滑动停止 */
            currentScrollState = scrollState;
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                scrollTo(0, 0);
                post(new Runnable() {
                    @Override
                    public void run() {
                        ensureScrollWheelAdjusted();
                    }
                });
            }
            if (mScrollListener != null) {
                mScrollListener.onScrollStateChanged(view, scrollState);
            }
        }
        
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                int visibleItemCount, int totalItemCount) {
            mVisibleItemCount = visibleItemCount;
            onPickerSelectedChange(getCurrentItem());
            if (mPenddingCurrentItem != firstVisibleItem && firstVisibleItem == 0
                    && visibleItemCount > 0 && mPenddingCurrentItem > 0) {
                setCurrentItemInternal(mPenddingCurrentItem);
                setPenddingCurrentItem(-1);
            }
            if (mScrollListener != null) {
                mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                        totalItemCount);
            }
        }
    };

   public int currentScrollState;
    
    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }
    
    private void setOnScrollListenerInternal(OnScrollListener l) {
        super.setOnScrollListener(l);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int newWidthMeasureSpec = widthMeasureSpec;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mFixedHeight
                * mSelectorCount + getPaddingTop() + getPaddingBottom(),
                MeasureSpec.EXACTLY);
        if (widthSpecMode != MeasureSpec.EXACTLY) {
            newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mFixedWidth
                    + getPaddingLeft() + getPaddingRight(), MeasureSpec.EXACTLY);
        }
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
    }
    
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, mFixedHeight);
    }
    
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams lp = super.generateLayoutParams(attrs);
        lp.width = LayoutParams.MATCH_PARENT;
        lp.height = mFixedHeight;
        return lp;
    }
    
    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p != null) {
            p.width = LayoutParams.MATCH_PARENT;
            p.height = mFixedHeight;
        }
        return super.generateLayoutParams(p);
    }
    
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        if (p != null) {
            p.width = LayoutParams.MATCH_PARENT;
            p.height = mFixedHeight;
        }
        return super.checkLayoutParams(p);
    }
    
    @Override
    protected void dispatchDraw(Canvas canvas) {
        final int width = getWidth();
        final int scrollX = getScrollX();
        final int scrollY = getScrollY();
        int saveCount = canvas.getSaveCount();
        
        int length = Math.max(mSelectorIndex * mFixedHeight,
                (mSelectorCount - 1 - mSelectorIndex) * mFixedHeight);
        int left = scrollX + getPaddingLeft();
        int top = scrollY + mSelectorIndex * mFixedHeight - length - getPaddingTop();
        int right = left + width - getPaddingLeft() - getPaddingRight();
        int bottom = top + 2 * length + mFixedHeight;
        
        boolean drawTop = mSelectorIndex > 0;
        boolean drawBottom = mSelectorIndex < mSelectorCount - 1;
        
//        final int flags = Canvas.HAS_ALPHA_LAYER_SAVE_FLAG;
        final int flags =  0x04;
        if (drawTop) {
            canvas.saveLayer(left, top, right, top + length, null, flags);
        }
        
        if (drawBottom) {
            canvas.saveLayer(left, bottom - length, right, bottom, null, flags);
        }
        
        super.dispatchDraw(canvas);
        
        if (drawTop) {
            mMatrix.setScale(1, length);
            mMatrix.postTranslate(left, top);
            mShader.setLocalMatrix(mMatrix);
            mPaint.setShader(mShader);
            canvas.drawRect(left, top, right, top + length, mPaint);
        }
        
        if (drawBottom) {
            mMatrix.setScale(1, length);
            mMatrix.postRotate(180);
            mMatrix.postTranslate(left, bottom);
            mShader.setLocalMatrix(mMatrix);
            mPaint.setShader(mShader);
            canvas.drawRect(left, bottom - length, right, bottom, mPaint);
        }
        canvas.restoreToCount(saveCount);
        
        if (mSelectorForeground != null) {
            canvas.save();
            mSelectorForeground.setBounds(scrollX, scrollY + mSelectorIndex
                    * mFixedHeight, width, scrollY + mSelectorIndex * mFixedHeight
                    + mFixedHeight);
            mSelectorForeground.draw(canvas);
            canvas.restore();
        }
    }



    public interface OnPickerSelectedListener {
        public void onPickerSelected(int position);
    }

}
