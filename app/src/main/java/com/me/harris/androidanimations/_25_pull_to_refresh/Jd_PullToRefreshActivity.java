package com.me.harris.androidanimations._25_pull_to_refresh;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/** 作者
 * https://github.com/shenminjie/jd_tmall_refresh_demo
 */
public class Jd_PullToRefreshActivity extends BaseAppCompatActivity {
    /**
     * 列表
     */
    RecyclerView mRecyclerView;

    /**
     * 下拉刷新
     */
    JdRefreshLayout mLayout;

//    /**
//     * 布局管理器
//     */
//    RecyclerView.LayoutManager mManager;

    /**
     * 数据
     */
    private List<Object> mDatas;

    /**
     * 适配器
     */
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd_pull_to_refresh);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatas=new ArrayList<>();
        for(int i=0;i<5;i++){
            mDatas.add(new Object());
        }

        mLayout = findViewById(R.id.test_recycler_view_frame);
        mRecyclerView = findViewById(R.id.test_recycler_view);
       /* mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);*/
        mAdapter=new MyAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                doSth();
                frame.postDelayed(frame::refreshComplete,5000);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    /**
     * demo
     */
    private void doSth() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mDatas.add(new Object());
                mAdapter.notifyDataSetChanged();
                mLayout.refreshComplete();
            }
        }.execute();
    }

    private static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /**
         * 数据
         */
        private List<Object> mDatas;

        public MyAdapter(List<Object> datas) {
            this.mDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jd_item_layout, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getContext(), "good", Toast.LENGTH_SHORT).show();
                }
            });
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }


}

