package com.me.harris.androidanimations.ui.recyclerview.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Fermi on 2016/9/21.
 */

public class MainAdapterItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paintBlue, paintRed;
    private int offset;


    public MainAdapterItemDecoration(Context context) {
        offset = Utils.dip2px(context, 10);
       /* paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setStrokeWidth(3);*/

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(1);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for(int i=0; i<parent.getChildCount(); i++){
            final View child = parent.getChildAt(i);
       /*     c.drawRect(
                    layoutManager.getDecoratedLeft(child),
                    layoutManager.getDecoratedTop(child),
                    layoutManager.getDecoratedRight(child),
                    layoutManager.getDecoratedBottom(child),
                    paintBlue);*/
            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + offset,
                    layoutManager.getDecoratedTop(child) + offset,
                    layoutManager.getDecoratedRight(child) - offset,
                    layoutManager.getDecoratedBottom(child) - offset,
                    paintRed);

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(offset, offset, offset, offset);
    }

}
