package com.me.harris.scrolllayoutsample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;

public class MaxLineTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MaxLineTextView(Context context) {
        super(context);
    }

    public MaxLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   public int maxLineNumber = 8;

    Rect rect = new Rect();
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int superHeight = getMeasuredHeight();
//        if (getLayout().getLineCount() > maxLineNumber) {
//            getLayout().getLineBounds(maxLineNumber - 1, rect);
////            int endPosition = getLayout().
////            setMeasuredDimension(getMeasuredWidth(),rect.bottom+getPaddingTop()+getPaddingBottom());
//        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // https://blog.csdn.net/htyxz8802/article/details/50387950
//        CharSequence charSequence = getText() ;
//        int lastCharDown = getLayout().getLineVisibleEnd(getLayout().getLineCount()-1) ;
//        if (charSequence.length() > lastCharDown){
//            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder() ;
//            spannableStringBuilder.append(charSequence.subSequence(0,lastCharDown-4)).append("...") ;
//            setText(spannableStringBuilder);
//        }
        super.onDraw(canvas);
        setText("",BufferType.SPANNABLE);
    }
}
