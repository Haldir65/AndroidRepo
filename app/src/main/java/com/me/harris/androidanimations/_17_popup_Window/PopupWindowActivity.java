package com.me.harris.androidanimations._17_popup_Window;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPopUpWindowBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2016/12/3.
 */

public class PopupWindowActivity extends BaseAppCompatActivity implements ActionCallBack {
    ActivityPopUpWindowBinding binding;

    private CustomPopWindow mCustomPopWindow;
    private CustomPopWindow mListPopWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pop_up_window);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button_1:

                break;
            case R.id.button_2:
                break;
            case R.id.button3:
                break;
            case R.id.button_4:
                break;
            default:
                break;
        }
    }


    private void showPopBottom(){
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(R.layout.pop_layout1)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create()
                .showAsDropDown(binding.button1,0,10);
    }

    private void showPopTop(){
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(R.layout.pop_layout2)
                .create();
        popWindow .showAsDropDown(binding.button2,0,  - (binding.button2.getHeight() + popWindow.getHeight()));
        //popWindow.showAtLocation(mButton1, Gravity.NO_GRAVITY,0,0);
    }

    private void showPopMenu(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(binding.button3,0,20);


    }

    private void showPopListView(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list,null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        mListPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                .create()
                .showAsDropDown(binding.button4,0,20);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        showContent = "点击 Item菜单1";
                        break;
                    case R.id.menu2:
                        showContent = "点击 Item菜单2";
                        break;
                    case R.id.menu3:
                        showContent = "点击 Item菜单3";
                        break;
                    case R.id.menu4:
                        showContent = "点击 Item菜单4";
                        break;
                    case R.id.menu5:
                        showContent = "点击 Item菜单5" ;
                        break;
                }
                Toast.makeText(PopupWindowActivity.this,showContent,Toast.LENGTH_SHORT).show();
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);
        contentView.findViewById(R.id.menu5).setOnClickListener(listener);
    }

    private void handleListView(View contentView){
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter();
        adapter.setData(mockData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private List<String> mockData(){
        List<String> data = new ArrayList<>();
        for (int i=0;i<100;i++){
            data.add("Item:"+i);
        }

        return data;
    }

    /**
     * Created by zhouwei on 16/11/30.
     */

    public static class MyAdapter extends RecyclerView.Adapter{
        private List<String> mData;

        public void setData(List<String> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;

            viewHolder.mTextView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0:mData.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            public TextView mTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text_content);
            }
        }
    }

}
