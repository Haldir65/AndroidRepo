package com.me.harris.androidanimations._34_bottomsheet;

import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityBottomSheetBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2017/5/6.
 */

public class BottomSheetActivity extends BaseAppCompatActivity implements BottomSheetItemAdapter.BottomSheetItemListener {
    ActivityBottomSheetBinding binding;

    private Button btnView, btnDialog;
    BottomSheetBehavior behavior;
    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetItemAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_sheet);
        btnView = (Button) findViewById(R.id.btnView);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new BottomSheetItemAdapter(createItems(), this);
        recyclerView.setAdapter(mAdapter);

    }

    private void showBottomSheetDialog() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BottomSheetItemAdapter(createItems(), new BottomSheetItemAdapter.BottomSheetItemListener() {
            @Override
            public void onItemClick(BottomSheetItem bottomSheetItem) {
                if (mBottomSheetDialog != null) {
                    mBottomSheetDialog.dismiss();
                }
            }
        }));

        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.setListener(null);
    }

    public List<BottomSheetItem> createItems() {

        ArrayList<BottomSheetItem> bottomSheetItems = new ArrayList<>();
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 1"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 2"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 3"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 4"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 5"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 6"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 7"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 8"));
        bottomSheetItems.add(new BottomSheetItem(R.mipmap.ic_launcher, "Item 9"));

        return bottomSheetItems;
    }

    @Override
    public void onItemClick(BottomSheetItem bottomSheetItem) {
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
