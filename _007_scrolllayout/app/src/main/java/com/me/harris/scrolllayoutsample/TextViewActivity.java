package com.me.harris.scrolllayoutsample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;

import com.me.harris.scrolllayoutsample.widget.MaxLineTextView;

public class TextViewActivity extends AppCompatActivity {

    MaxLineTextView mTextView;

    public static final String  LONG_CONTENT = "2018年第一季度，腾讯网络游戏收入增长26%至人民币287.78亿元，占总营收的比重为39.14%，继上一季度后再次低于40%，但较上季度36%的占比有所回升。\n" +
            "腾讯表示，该项增长主要受手游《王者荣耀》等现有游戏以及《奇迹MU：觉醒》与《QQ飞车手游》等新游戏收入的增长所推动。财报显示，智能手机游戏收入（包括归属于社交网络业务的智能手机游戏收入）约达人民币217亿元，同比增长68%。受益于季度推广活动及新游戏，智能手机游戏收入环比增长28%。\n" +
            "腾讯每季度财报都会有其董事会主席兼CEO马化腾对当季财报的评价，这次亦不例外，并且主要是谈游戏收入：“ 在2018年第一季度，我们推出了广受欢迎的战术竞技类手游，并提升了如微信小程序等被广泛应用的服务的功能，进一步加强我们社交、游戏和媒体平台上的用户活跃度。”\n" +
            "与此同时，随着本季度游戏业务收入的显著增长，直接助推腾讯游戏业务年化收入突破1000亿元大关，达到人民币1038.5亿元（2017Q2、Q3、Q4和2018Q1分别为238.61亿元、268.44亿元、243.67亿元和287.78亿元）。2017全年腾讯游戏收入为978.8亿元。\n" +
            "据虎嗅了解，腾讯内部希望淡化“游戏色彩”，一是游戏的名声不好听，从网友将腾讯热门游戏《王者荣耀》戏称为“王者农药”等可见一斑；二是经过人民网N评《王者荣耀》后，腾讯对游戏的增长颇有些“谈虎色变”，游戏收入越高，则意味着越“不正确”，具有潜在的政策风险。\n" +
            "但投资者则希望腾讯的游戏收入保持高水平增长，这也是腾讯纠结之所在。";

    public static final String SHORT_CONTENT = "与此同时，随着本季度游戏业务收入的显著增长，直接助推腾讯游戏业务年化收入突破1000亿元大关，达到人民币1038.5亿元";

    public static final String MIDDLE_CONTENT = "2018年第一季度，腾讯网络游戏收入增长26%至人民币287.78亿元，占总营收的比重为39.14%，继上一季度后再次低于40%，但较上季度36%的占比有所回升。\n" +
            "腾讯表示，该项增长主要受手游《王者荣耀》等现有游戏以及《奇迹MU：觉醒》与《QQ飞车手游》等新游戏收入的增长所推动。财报显示，智能手机游戏收入（包括归属于社交网络业务的智能手机游戏收入）约达人";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        mTextView = findViewById(R.id.textView);

        SpannableString spanString = new SpannableString("pics"+LONG_CONTENT);
        Drawable drawable = getResources().getDrawable(R.drawable.emoji_0);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());//忘记这语句就不显示drawable
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(imageSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.md_yellow_700));
        spanString.setSpan(foregroundColorSpan,20,24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(ContextCompat.getColor(this, R.color.md_red_800));
        spanString.setSpan(backgroundColorSpan,28,34,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(spanString);
        mTextView.setMaxLines(8);
        mTextView.setEllipsize(TextUtils.TruncateAt.END);
    }
}
