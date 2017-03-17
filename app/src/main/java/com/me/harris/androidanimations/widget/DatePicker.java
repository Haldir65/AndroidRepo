package com.me.harris.androidanimations.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.harris.androidanimations.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DatePicker extends LinearLayout {
    
    private static SimpleDateFormat sWeekFormat = new SimpleDateFormat("E");
    
    enum DateType {
        YEAR, MONTH, DAY
    }
    
    private PickerView mYearPicker;
    private PickerView mMonthPicker;
    private PickerView mDayPicker;
    
    private int mYear;
    private int mPenddingYear;
    private int mMonth;
    private int mPenddingMonth;
    private int mDay;
    
    private boolean mYearVisible = true;
    private boolean mMonthVisible = true;
    private boolean mDayVisible = true;
    
    private boolean mNeedRemoveMonthFromNow = false;// 是否删除超过本月的月份
    private boolean mSupportYearUntilNow = false;// 是否支持年份为至今
    private boolean mSupportMonthFromStartTime = false;//是否支持月份从开始时间的月份之后设置
    private boolean mSupportMonthBeforeEndTime = false;//是否支持月份从结束时间的月份之前设置
    private boolean mEndTimeIsUntil = false;//结束时间是否是至今
    private boolean mOnlyUntil = false;
    public static final int YEAR_UNTIL_NOW = -1;
    
    private int mMinYear;
    private int mMaxYear;
    private int mLastYear;
    private int mLastMonth;
    
    private boolean mFirst = true;
    
    private List<Integer> mDayDatas = new ArrayList<Integer>();
    private List<Integer> mMonthData = new ArrayList<>();
    
    public DatePicker(Context context) {
        this(context, null);
    }
    
    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        final LayoutInflater inflater = LayoutInflater.from(context);
        LayoutParams lp;
        
        mYearPicker = (PickerView) inflater.inflate(R.layout.view_date_picker, this,
                false);
        lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(mYearPicker, lp);
        mYearPicker.setOnPickerSelectedListener(mYearSelectedListener);
        
        mMonthPicker = (PickerView) inflater.inflate(R.layout.view_date_picker, this,
                false);
        lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(mMonthPicker, lp);
        mMonthPicker.setOnPickerSelectedListener(mMonthSelectedListener);
        
        mDayPicker = (PickerView) inflater
                .inflate(R.layout.view_date_picker, this, false);
        lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(mDayPicker, lp);
        mDayPicker.setOnPickerSelectedListener(mDaySelectedListener);
        
        setBackgroundColor(getResources().getColor(R.color.white));
    }
    
    public void setDate(int year, int minYear, int maxYear) {
        setDate(year, 1, 1, minYear, maxYear);
    }
    
    public void setDate(int year, int month, int day, int minYear, int maxYear) {
        if (maxYear < minYear) {
            throw new RuntimeException("maxYear < minYear");
        }
        mMaxYear = maxYear;
        mMinYear = minYear;
        if (year < minYear) {
            mYear = minYear;
        }
        else if (year > maxYear) {
            mYear = maxYear;
        }
        else {
            mYear = year;
        }
        mPenddingYear = mYear;
        mPenddingMonth = mMonth = month;
        mDay = day;
        
        mDayPicker.setAdapter(createDayAdapter(year, month));
        mDayPicker.setCurrentItem(day - 1);
        
        mMonthPicker.setAdapter(createMonthAdapter());
        mMonthPicker.setCurrentItem(month - 1);
        mYearPicker.setAdapter(createYearAdapter());
        mYearPicker.setCurrentItem(year-mMinYear);
    }

    public DatePicker setNeedRemoveMonthFromNow() {
        mNeedRemoveMonthFromNow = true;
        return this;
    }
    
    public DatePicker setSupportYearUntilNow() {
        mSupportYearUntilNow = true;
        return this;
    }
    public DatePicker setOnlyUntil(){
        mOnlyUntil = true;
        return this;
    }
    public DatePicker setSupportMonthFromStartTime(){
        mSupportMonthFromStartTime = true;
        return this;
    }
    public DatePicker setSupportMonthBeforeEndTime(){
        mSupportMonthBeforeEndTime = true;
        return this;
    }
    public DatePicker setEndTimeUntil(){
        mEndTimeIsUntil = true;
        return this;
    }
    public void setLastYearAndMonth(int lastYear,int lastMonth){
        this.mLastYear = lastYear;
        this.mLastMonth = lastMonth;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFirst = false;
        return super.dispatchTouchEvent(ev);
    }
    
    public int getYear() {
        return mYear;
    }
    
    public int getMonth() {
        return mMonth;
    }
    
    public int getDay() {
        return mDay;
    }
    
    public void setDateVisible(boolean yearVisible, boolean monthVisible,
            boolean dayVisible) {
        mYearVisible = yearVisible;
        mMonthVisible = monthVisible;
        mDayVisible = dayVisible;
        
        mYearPicker.setVisibility(mYearVisible ? VISIBLE : GONE);
        mMonthPicker.setVisibility(mMonthVisible ? VISIBLE : GONE);
        mDayPicker.setVisibility(mDayVisible ? VISIBLE : GONE);
    }
    
    private PickerView.OnPickerSelectedListener mYearSelectedListener = new PickerView.OnPickerSelectedListener() {
        
        @Override
        public void onPickerSelected(int position) {
            
            if (mSupportYearUntilNow
                    && mMaxYear == Calendar.getInstance().get(Calendar.YEAR)
                    && position == mYearPicker.getPickerCount() - 1) {
                mMonthData.clear();
                mMonthPicker.notifyDataChange();
                mDayDatas.clear();
                mDayPicker.notifyDataChange();
                mYear = YEAR_UNTIL_NOW;
                return;
            }
            
            mYear = position + mMinYear;
            
            if (mNeedRemoveMonthFromNow) {
                if (mMonthVisible && !mFirst) {
                    int months = getMonths(mYear);
                    if (months != mMonthPicker.getPickerCount()) {
                        setupMonthData(mYear);
                        int curItem = mMonthPicker.getCurrentItem();
                        if (curItem >= mMonthData.size())
                            mMonthPicker.setCurrentItem(mMonthData.size() - 1);
                        mMonthPicker.notifyDataChange();
                    }
                }
            }
            
            int monthDays = getMonthDays(mYear, mMonth);
            if (mDayVisible && !mFirst) {
                if (monthDays != mDayPicker.getPickerCount()) {
                    setupDayDatas(monthDays);
                    mDayPicker.setCurrentItem(mDay - 1);
                    mDayPicker.notifyDataChange();
                }
            }
            else if (mFirst) {
                mDayPicker.notifyDataChange();
            }
        }
    };
    
    private PickerView.OnPickerSelectedListener mMonthSelectedListener = new PickerView.OnPickerSelectedListener() {
        
        @Override
        public void onPickerSelected(int position) {
            mMonth = position + 1;
            if (mDayVisible && !mFirst) {
                int monthDays = getMonthDays(mYear, mMonth);
                if (monthDays != mDayPicker.getPickerCount()) {
                    setupDayDatas(monthDays);
                    mDayPicker.setCurrentItem(mDay - 1);
                    mDayPicker.notifyDataChange();
                }
            }
            else if (mFirst) {
                mDayPicker.notifyDataChange();
            }
        }
    };
    
    private PickerView.OnPickerSelectedListener mDaySelectedListener = new PickerView.OnPickerSelectedListener() {
        
        @Override
        public void onPickerSelected(int position) {
            mDay = position + 1;
        }
    };
    
    DateAdapter createYearAdapter() {
        List<Integer> datas = new ArrayList<Integer>();
        if(!mOnlyUntil){
            for (int i = mMinYear; i <= mMaxYear; i++) {
                datas.add(i);
            }
        }else{
            mYear= YEAR_UNTIL_NOW;
        }
        if (mSupportYearUntilNow && mMaxYear == Calendar.getInstance().get(Calendar.YEAR)) {
            datas.add(YEAR_UNTIL_NOW);
        }
        
        return new DateAdapter(getContext(), datas, DateType.YEAR);
    }
    
    DateAdapter createMonthAdapter() {
        mMonthData.clear();
        int fromMonth = 1;
        int toMonth = 12;
        if(mSupportMonthFromStartTime){
            if(!mEndTimeIsUntil && mYear ==Calendar.getInstance().get(Calendar.YEAR)){
                fromMonth = mLastMonth+1;
            }
            if(mLastYear == Calendar.getInstance().get(Calendar.YEAR)){
                toMonth =  Calendar.getInstance().get(Calendar.MONTH)+1;
            }
        }else if(mSupportMonthBeforeEndTime){

            if(mMaxYear == Calendar.getInstance().get(Calendar.YEAR)){
                toMonth =  Calendar.getInstance().get(Calendar.MONTH)+1;
            }else if(mLastMonth == 0){
                toMonth = 12;
            }else{
                if(mYear ==Calendar.getInstance().get(Calendar.YEAR)){
                    toMonth = mLastMonth;
                }
            }

        }else{
            toMonth = getMonths(mYear);
        }
        for (int i = fromMonth; i <= toMonth; i++) {
            mMonthData.add(i);
        }
        return new DateAdapter(getContext(), mMonthData, DateType.MONTH);
    }
    
    DateAdapter createDayAdapter(int year, int month) {
        return createDayAdapter(getMonthDays(year, month));
    }
    
    DateAdapter createDayAdapter(int monthDays) {
        mDayDatas.clear();
        for (int i = 1; i <= monthDays; i++) {
            mDayDatas.add(i);
        }
        return new DateAdapter(getContext(), mDayDatas, DateType.DAY);
    }
    
    private void setupDayDatas(int monthDays) {
        mDayDatas.clear();
        for (int i = 1; i <= monthDays; i++) {
            mDayDatas.add(i);
        }
    }
    
    private void setupMonthData(int year) {
        mMonthData.clear();
        int fromMonth = 1;
        int toMonth = 12;
        if(mSupportMonthFromStartTime){
            if(year == mLastYear){
                fromMonth = mLastMonth+1;
            }
            if(year == Calendar.getInstance().get(Calendar.YEAR)){
                toMonth =  Calendar.getInstance().get(Calendar.MONTH)+1;
            }
        }else{
            if(mSupportMonthBeforeEndTime){
                if(year == Calendar.getInstance().get(Calendar.YEAR)){
                    toMonth =  Calendar.getInstance().get(Calendar.MONTH)+1;
                }else if(year == mLastYear){
                    toMonth = mLastMonth;
                }
            }else{
                toMonth = getMonths(year);
            }

        }
        for (int i = fromMonth; i <= toMonth; i++) {
            mMonthData.add(i);
        }
    }
    
    class DateAdapter extends BaseAdapter<Integer> {
        private DateType mType;
        
        public DateAdapter(Context context, List<Integer> list, DateType type) {
            super(context, list);
            mType = type;
        }
        
        @Override
        public View createView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_date_picker,
                    parent, false);
        }
        
        @Override
        public BaseAdapter.ViewHolder<Integer> createViewHolder() {
            return new DateHolder();
        }
        
        class DateHolder extends BaseAdapter.ViewHolder<Integer> {
            
            TextView textView;
            
            @Override
            public void init(Context context, View convertView) {
                textView = (TextView) convertView;
            }
            
            @Override
            public void update(Context context, Integer data) {
                textView.setText(getDateText(data, mType));
            }
        }
        
        public String getDateText(int data, DateType type) {
            
            if (type == DateType.YEAR && mSupportYearUntilNow && data == YEAR_UNTIL_NOW) {
                return getResources().getString(R.string.date_until_now);
            }
            
            StringBuilder sb = new StringBuilder(Integer.toString(data));
            if (type != null) {
                switch (type) {
                    case YEAR:
                        sb.append("年");
                        break;
                    case MONTH:
                        sb.append("月");
                        break;
                    case DAY:
                        sb.append("日");
                        sb.append(sWeekFormat.format(new Date(getTime(
                                mFirst ? mPenddingYear : mYear, mFirst ? mPenddingMonth
                                        : mMonth, data))));
                        break;
                    default:
                        break;
                }
            }
            return sb.toString();
        }
        
    }
    
    public static long getTime(int year, int month, int day) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.clear();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTimeInMillis();
    }
    
    public static int getMonthDays(int year, int month) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.clear();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public int getMonths(int year) {
        if (mNeedRemoveMonthFromNow && Calendar.getInstance().get(Calendar.YEAR) == year) {
            int curMonth = Calendar.getInstance().get(Calendar.MONTH);
            return (curMonth + 1);
        }
        else {
            if(mSupportMonthFromStartTime){
                if(year == mLastYear){
                    return 12-mLastMonth;
                }else{
                    return 12;
                }
            }else{
                if(mSupportMonthBeforeEndTime){
                    if(year == mLastYear){
                        return mLastMonth-1;
                    }else{
                        return 12;
                    }
                }else{
                    return 12;
                }

            }
        }
    }

}
