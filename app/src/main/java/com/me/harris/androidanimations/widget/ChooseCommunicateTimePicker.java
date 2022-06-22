package com.me.harris.androidanimations.widget;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.harris.androidanimations.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用于在助教端 助教和老师新增沟通时间的Picker
 */
public class ChooseCommunicateTimePicker extends LinearLayout implements AbsListView.OnScrollListener {

    private PickerView mYearPicker;
    private PickerView mMonthPicker;
    private PickerView mDayPicker;

    int mYear; //当前的年份 例如2017

    int mMonth; //当前的月份 例如2月

    int mDay; //当前的日期 例如12号

    final int currentYear, currentMonth, currentDay;//记录今天的时间 ，不允许今天之后的时间

    int minYear; //minYear必传

    private boolean mFirst = true; //目前存在刚进来不能滑动到指定位置的问题

    List<Integer> mYearsList = new ArrayList<>();
    List<Integer> mMonthsList = new ArrayList<>();
    List<Integer> mDayList = new ArrayList<>();
    private MonthAdapter monthAdapter;
    private DayAdapter dayAdapter;
    private YearAdapter yearAdapter;

    public ChooseCommunicateTimePicker(Context context) {
        this(context, null);
    }

    public ChooseCommunicateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        final LayoutInflater inflater = LayoutInflater.from(context);
        LayoutParams lp;
        mYearPicker = (PickerView) inflater.inflate(R.layout.view_date_picker, this,
                false);
        lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(mYearPicker, lp);
        mYearPicker.setOnPickerSelectedListener(mYearSelectedListener);
        mYearPicker.setOnScrollListener(this);
        mMonthPicker = (PickerView) inflater.inflate(R.layout.view_date_picker, this,
                false);
        lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(mMonthPicker, lp);
        mMonthPicker.setOnPickerSelectedListener(mMonthSelectedListener);
        mMonthPicker.setOnScrollListener(this);
        mDayPicker = (PickerView) inflater.inflate(R.layout.view_date_picker, this,
                false);
        lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(mDayPicker, lp);
        mDayPicker.setOnPickerSelectedListener(mDaySelectedListener);
        mDayPicker.setOnScrollListener(this);
        setBackgroundColor(getResources().getColor(R.color.white));
        minYear = 2010; //最早年份默认设置成2010年
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;//Calendar获取的month要加一的
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param year    2017年就输入2017
     * @param month   2月份就输入2月份
     * @param day     27号就输入27号
     * @param minYear 最小年份
     */
    public void setDate(int year, int month, int day, int minYear) {
        if (minYear > currentYear) {
            throw new RuntimeException("minYear > currentYear");
        }
        if (mYear > currentYear) {
            throw new RuntimeException("不能选择未来的时间");
        }
        this.minYear = minYear;
        mYear = year;
        mMonth = month;
        mDay = day;
        yearAdapter = createYearAdapter(minYear, currentYear);
        mYearPicker.setAdapter(yearAdapter);
        mYearPicker.setSelection(mYear - minYear);
        if (mMonthsList == null) {
            mMonthsList = new ArrayList<>();
        } else {
            mMonthsList.clear();
        }
        if (mDayList == null) {
            mDayList = new ArrayList<>();
        } else {
            mDayList.clear();
        }
        if (mYear == currentYear) {
            //月份要进行限制
            if (mMonth <= currentMonth) {
                for (int i = 0; i < currentMonth; i++) {
                    mMonthsList.add(i);
                }
                monthAdapter = new MonthAdapter(getContext(), mMonthsList);
                mMonthPicker.setAdapter(monthAdapter);
//                mMonthPicker.setCurrentItem(mMonth - 1);
                mMonthPicker.setSelection(mMonth - 1);
                if (mMonth == currentMonth) {
                    if (mDay <= currentDay) {
                        for (int i = 0; i < currentDay; i++) {
                            mDayList.add(i);
                        }
                        dayAdapter = new DayAdapter(getContext(), mDayList);
                        mDayPicker.setAdapter(dayAdapter);
//                        mDayPicker.setCurrentItem(mDay - 1);
                        mDayPicker.setSelection(mDay - 1);
                    } else {
                        throw new RuntimeException("不允许选择未来的时间");
                    }
                } else {
                    dayAdapter = createDayAdapter();
                    mDayPicker.setAdapter(dayAdapter);
//            mDayPicker.setCurrentItem(mDay - 1);
                    mDayPicker.setSelection(mDay - 1);
                }
            } else {
                throw new RuntimeException("不允许选择未来的时间");
            }
        } else {
            monthAdapter = createMonthAdapter();
            mMonthPicker.setAdapter(monthAdapter);
//            mMonthPicker.setCurrentItem(mMonth - 1);
            mMonthPicker.setSelection(mMonth - 1);
            dayAdapter = createDayAdapter();
            mDayPicker.setAdapter(dayAdapter);
//            mDayPicker.setCurrentItem(mDay - 1);
            mDayPicker.setSelection(mDay - 1);
        }
    }

    /**
     * @return 获取当前的日 0 表示 1号，28代表29号。。。。
     */
    public int getDay() {
        return mDay;
    }

    /**
     * @return 获取当前的月份 0代表1月，11代表2月
     */
    public int getMonth() {
        return mMonth;
    }

    /**
     * 获取当前的年份 2017 就是 2017年
     *
     * @return
     */
    public int getYear() {
        return mYear;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFirst = false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                /*if (mDayPicker.getCurrentItem() != mDay-1) {
                    mDayPicker.setCurrentItem(mDay-1);
                }
                if (mYearPicker.getCurrentItem() != mYear-minYear) {
                    mYearPicker.setCurrentItem(mYear-minYear);
                }
                if (mMonthPicker.getCurrentItem() != mMonth-1) {
                    mMonthPicker.setCurrentItem(mMonth-1);
                }*/
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public interface onDateChangeListener {
        void onDateChanged(ChooseCommunicateTimePicker view, int year, int monthOfYear, int dayOfMonth);
    }

    public onDateChangeListener monDateChangeListener;

    /**
     * @return 返回的是0-31这种，0表示当天，1表示明天，依次往后推
     */

    private PickerView.OnPickerSelectedListener mYearSelectedListener = new PickerView.OnPickerSelectedListener() {

        @Override
        public void onPickerSelected(int position) {
           /* if (mYearPicker.currentScrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                return;
            }*/
            if (mFirst) {
                if (position == mYear - minYear && mMonthPicker.getCurrentItem() != mMonth - 1) {
//                    mMonthPicker.setCurrentItem(mMonth-1);
                }
            } else {
                mYear = position + minYear;
                if (mYear == currentYear) {
                    if (mMonth > currentMonth) {
                        mMonth = currentMonth;
                    }
                    if (currentMonth != 12) {
                        mMonthsList.clear();
                        for (int i = 0; i < currentMonth; i++) {
                            mMonthsList.add(i);
                        }
                        monthAdapter.notifyDataSetChanged();
//                        mMonthPicker.setCurrentItem(mMonth - 1);
                        mMonthPicker.setSelection(currentMonth-1);
                        int size = getDaysOfMonth(mYear, mMonth);
                        if (mMonth == currentMonth) {
                            size = currentDay;
                        }
                        if (size != mDayList.size()) {
                            mDayList.clear();
                            for (int i = 0; i < size; i++) {
                                mDayList.add(i);
                            }
                            dayAdapter.notifyDataSetChanged();
                            if (mDay > size) {
                                mDay = size;
                                mDayPicker.setSelection(size - 1);
                            }
                        }
                    }
                } else {// 当前年份不是今年，月份恢复12，
                    if (mMonthsList.size() != 12) {
                        mMonthsList.clear();
                        for (int i = 0; i < 12; i++) {
                            mMonthsList.add(i);
                        }
                        monthAdapter.notifyDataSetChanged();
                        mMonthPicker.setCurrentItem(mMonth - 1);
                    }
                    int size = getDaysOfMonth(mYear, mMonth);
                    if (mDayList.size() != size) {
                        mDayList.clear();
                        for (int i = 0; i < size; i++) {
                            mDayList.add(i);
                        }
                        dayAdapter.notifyDataSetChanged();
                        if (mDay > size) {
                            mDay = size;
                        }
                        mDayPicker.setCurrentItem(mDay - 1);
                    }
                }
            }
            if (monDateChangeListener != null) {
                monDateChangeListener.onDateChanged(ChooseCommunicateTimePicker.this, mYear, mMonth, mDay);
            }
        }
    };

    private PickerView.OnPickerSelectedListener mMonthSelectedListener = new PickerView.OnPickerSelectedListener() {

        @Override
        public void onPickerSelected(int position) {
           /* if (mMonthPicker.currentScrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                return;
            }*/
            if (mFirst) {
                if (position == mMonth - 1 && mDayPicker.getCurrentItem() != mDay - 1) {
//                    mDayPicker.setCurrentItem(mDay-1);
                }
            } else {
                mMonth = position + 1;
                int size = getDaysOfMonth(mYear, mMonth);
                if (mYear == currentYear && mMonth == currentMonth) {
                    size = Math.min(currentDay, size);
                }
                if (mDayList.size() != size) {
                    mDayList.clear();
                    for (int i = 0; i < size; i++) {
                        mDayList.add(i);
                    }
                    dayAdapter.notifyDataSetChanged();
                }
                if (mDay > size) {
                    mDay = size;
//                    mDayPicker.setCurrentItem(mDay-1);
                }
            }
            if (monDateChangeListener != null) {
                monDateChangeListener.onDateChanged(ChooseCommunicateTimePicker.this, mYear, position + 1, mDay);
            }
        }
    };

    private PickerView.OnPickerSelectedListener mDaySelectedListener = new PickerView.OnPickerSelectedListener() {

        @Override
        public void onPickerSelected(int position) {
            /*if (mDayPicker.currentScrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                return;
            }*/
            mDay = position + 1;
            if (monDateChangeListener != null) {
                monDateChangeListener.onDateChanged(ChooseCommunicateTimePicker.this, mYear, mMonth, mDay);
            }
        }
    };

    /**
     * @param year
     * @param month 就按照1月、2月这样传进来
     * @return 这个月有多少天
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    YearAdapter createYearAdapter(int minYear, int maxYear) {
        if (mYearsList == null) {
            mYearsList = new ArrayList<>();
        } else {
            mYearsList.clear();
        }
        int size = maxYear - minYear + 1;
        for (int i = 0; i < size; i++) {
            mYearsList.add(i);
        }
        return new YearAdapter(getContext(), mYearsList, minYear, maxYear);
    }

    MonthAdapter createMonthAdapter() {
        if (mMonthsList == null) {
            mMonthsList = new ArrayList<>();
        } else {
            mMonthsList.clear();
        }
        for (int i = 0; i < 12; i++) {
            mMonthsList.add(i);
        }
        return new MonthAdapter(getContext(), mMonthsList);
    }

    DayAdapter createDayAdapter() {
        if (mDayList == null) {
            mDayList = new ArrayList<>();
        } else {
            mDayList.clear();
        }
        int size = getDaysOfMonth(mYear, mMonth);
        for (int i = 0; i < size; i++) {
            mDayList.add(i);
        }
        return new DayAdapter(getContext(), mDayList);
    }

    static class YearAdapter extends com.me.harris.androidanimations.widget.BaseAdapter<Integer> {

        int minYear, maxYear;//minYear必传

        public YearAdapter(Context context, List<Integer> list, int minYear, int maxYear) {
            super(context, list);
            this.minYear = minYear;
            this.maxYear = maxYear;
        }

        @Override
        public View createView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_date_picker,
                    parent, false);
        }

        @Override
        public com.me.harris.androidanimations.widget.BaseAdapter.ViewHolder<Integer> createViewHolder() {
            return new YearTextHolder(minYear, maxYear);
        }
    }

    static class YearTextHolder extends com.me.harris.androidanimations.widget.BaseAdapter.ViewHolder<Integer> {
        TextView textView;
        int minYear, maxYear;

        public YearTextHolder(int minYear, int maxYear) {
            this.minYear = minYear;
            this.maxYear = maxYear;
        }

        @Override
        public void init(Context context, View convertView) {
            textView = (TextView) convertView;
            textView.setTextColor(ContextCompat.getColor(context, R.color.accent_orange));
        }

        @Override
        public void update(Context context, Integer data) {
            int curYear = minYear + data;
            textView.setText(curYear + "年");
        }
    }

    static class MonthAdapter extends com.me.harris.androidanimations.widget.BaseAdapter<Integer> {

        public MonthAdapter(Context context, List<Integer> list) {
            super(context, list);
        }

        @Override
        public View createView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_date_picker,
                    parent, false);
        }

        @Override
        public com.me.harris.androidanimations.widget.BaseAdapter.ViewHolder<Integer> createViewHolder() {
            return new MonthTextHolder();
        }
    }

    static class MonthTextHolder extends com.me.harris.androidanimations.widget.BaseAdapter.ViewHolder<Integer> {
        TextView textView;

        @Override
        public void init(Context context, View convertView) {
            textView = (TextView) convertView;
            textView.setTextColor(ContextCompat.getColor(context, R.color.accent_orange));
        }

        @Override
        public void update(Context context, Integer data) {
            textView.setText(getMonthText(data));
        }
    }

    static class DayAdapter extends com.me.harris.androidanimations.widget.BaseAdapter<Integer> {

        public DayAdapter(Context context, List<Integer> list) {
            super(context, list);
        }

        @Override
        public View createView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_date_picker,
                    parent, false);
        }

        @Override
        public ViewHolder<Integer> createViewHolder() {
            return new DayTextHolder();
        }
    }

    static class DayTextHolder extends com.me.harris.androidanimations.widget.BaseAdapter.ViewHolder<Integer> {
        TextView textView;

        @Override
        public void init(Context context, View convertView) {
            textView = (TextView) convertView;
            textView.setTextColor(ContextCompat.getColor(context, R.color.accent_orange));
        }

        @Override
        public void update(Context context, Integer data) {
            textView.setText(getDayText(data));
        }
    }

    /**
     * @param data 输入0 返回1月 ，11返回12月
     * @return
     */
    public static String getMonthText(int data) {
        String result = "";
        if (data < 12 && data > -1) {
            data++;
            result = data + "月";
        }
        return result;
    }

    static String getDayText(int data) {
        String result = "";
        if (data > -1 && data <= 31) {
            data++;
            result = data + "日";
        }
        return result;
    }

    /**
     * @param input 距离今天还有多少天，例如明天就是1，后天是2 ，只能是0-maxDate
     * @return 根据距离今天还有几天返回周一、周三等等
     */
    public static String getDayOfWeek(int input) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, input);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            case Calendar.SUNDAY:
                return "周日";
            default:
                return "";
        }
    }

    /**
     * 返回1月16号 这种
     *
     * @param input 距离今天还有几天
     * @return
     */
    public static String getMonthAndDateText(int input) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, input);
        Date date = new Date(calendar.getTimeInMillis());
        String result = mdSdf.format(date);
        return result;
    }

    public static SimpleDateFormat mdSdf = new SimpleDateFormat("MM月dd日");
}
