package com.me.harris.androidanimations.propertyanimations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPropertyBinding;
import com.me.harris.androidanimations.utils.ActionCallBack;
import com.me.harris.androidanimations.utils.Utils;

import static android.animation.ValueAnimator.REVERSE;

/**
 * Created by Harris on 2016/9/13.
 *  参考 http://blog.csdn.net/yegongheng/article/details/38435553
 *   https://developer.android.com/guide/topics/graphics/prop-animation.html#value-animator
 *   propertyAnimation的xml文件必须放在R/res/animator文件夹下面，不是anim文件夹
 *   mImageView.animate().rotationX(360.0f).setDuration(3000).start();这种是最简单的方式
 *
 *   http://blog.csdn.net/yegongheng/article/details/38455191 关于LayoutTransition的自定义
 *   KeyFrame问题
 */

public class PropertyAnimationActivity extends AppCompatActivity implements ActionCallBack {
    ActivityPropertyBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property);
        binding.setCallback(this);
        setTitle(getClass().getSimpleName());

    }


    /**
     * @param v
     */
    @Override
    public void onClickView(final View v) {
        switch (v.getId()) {
            case R.id.imageView:
                final ObjectAnimator animator = ObjectAnimator
                        .ofFloat(v, "WTF", 1f, 0.5f).setDuration(1500);//写成WTF不会崩，但还是少这么干
               // ObjectAnimator visibleToInVisable = ObjectAnimator.ofFloat(v , "rotationX", 0.0f,90.0f);
               // final ObjectAnimator invisibleToVisible = ObjectAnimator.ofFloat(, "rotationX", -90.0f,0.0f);
                //   ObjectAnimator mAnimatorAlpha = ObjectAnimator.ofFloat(alphaListView, "alpha", 1.0f,0.0f);
                // ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(scaleListView, "scaleX", 1.0f,0.0f);
                //
                //
                //
                animator.setRepeatCount(2);
                animator.setRepeatMode(REVERSE);
                animator.start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float cValue = (float) animation.getAnimatedValue();
                        v.setAlpha(cValue);
                        v.setScaleX(1.2f);
                        v.setScaleY(1.2f);
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }
                });
                break;
            case R.id.imageView2:
                ObjectAnimator animX = ObjectAnimator.ofFloat(v, "x", 50f);
                animX.setInterpolator(new CustomInterpolator());  // Interpolartor用于控制时间输出，作为Evaluator的输入参数
                ObjectAnimator animY = ObjectAnimator.ofFloat(v, "y", 100f);
                animY.setEvaluator(new CustomEvaluator()); //Evaluator用于根据输出的时间fraction控制最终输出的对象属性的变化
                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();
                break;
            case R.id.imageView3:
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
                ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY).start();
                break;
            case R.id.imageView4:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    v.animate().x(v.getX()+50f).y(v.getY()+100f).z(120);
                }
                break;
            case R.id.imageView5:
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                        R.animator.sequential_effects);
                set.setTarget(v);
                set.start();
                break;
            case R.id.imageView6:
                ValueAnimator animator6 = ValueAnimator.ofInt(0, Utils.getScreenWidth(this) - v.getWidth() );
                animator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatorValue = (int)animation.getAnimatedValue();
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                        marginLayoutParams.leftMargin = animatorValue;
                        v.setLayoutParams(marginLayoutParams);
                    }
                });
                animator6.setInterpolator(new AccelerateDecelerateInterpolator());
                animator6.setEvaluator(new FloatEvaluator());
                animator6.setDuration(3000);
                animator6.setTarget(v);
                animator6.start();
                break;
            case R.id.imageView7:
                break;
            case R.id.imageView8:
                break;
            case R.id.imageView9:
                break;
            default:
                break;

        }
    }

   /* *//**
     * 翻转动画效果
     *//*
    public void flip(){
        final ListView visibleView;
        final ListView invisibleView;
        if(mListViewFront.getVisibility() == View.GONE){
            visibleView = mListViewReverse;
            invisibleView = mListViewFront;
        }else{
            visibleView = mListViewFront;
            invisibleView = mListViewReverse;
        }
        //创建ListView从Visible到Gone的动画
        ObjectAnimator visibleToInVisable = ObjectAnimator.ofFloat(visibleView, "rotationX", 0.0f,90.0f);
        //设置插值器
        visibleToInVisable.setInterpolator(new AccelerateInterpolator());
        visibleToInVisable.setDuration(500);

        //创建ListView从Gone到Visible的动画
        final ObjectAnimator invisibleToVisible = ObjectAnimator.ofFloat(invisibleView, "rotationX", -90.0f,0.0f);
        //设置插值器
        invisibleToVisible.setInterpolator(new DecelerateInterpolator());
        invisibleToVisible.setDuration(500);

        visibleToInVisable.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                super.onAnimationEnd(animation);
                visibleView.setVisibility(View.GONE);
                invisibleToVisible.start();
                invisibleView.setVisibility(View.VISIBLE);
            }
        });
        visibleToInVisable.start();
    }

    *//**
     * 渐变动画效果
     *//*
    public void alphaAnimator(){
        ListView alphaListView = null;
        if(mListViewFront.getVisibility() == View.GONE){
            alphaListView = mListViewReverse;
        }else{
            alphaListView = mListViewFront;
        }
        //1、通过调用ofFloat()方法创建ObjectAnimator对象，并设置目标对象、需要改变的目标属性名、初始值和结束值；
        ObjectAnimator mAnimatorAlpha = ObjectAnimator.ofFloat(alphaListView, "alpha", 1.0f,0.0f);
        //2、设置动画的持续时间、是否重复及重复次数属性；
        mAnimatorAlpha.setRepeatMode(Animation.REVERSE);
        mAnimatorAlpha.setRepeatCount(3);
        mAnimatorAlpha.setDuration(1000);
        //3、启动动画
        mAnimatorAlpha.start();
    }

    *//**
     * 伸缩动画效果
     *//*
    public void scaleAnimator(){
        ListView scaleListView = null;
        if(mListViewFront.getVisibility() == View.GONE){
            scaleListView = mListViewReverse;
        }else{
            scaleListView = mListViewFront;
        }

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(scaleListView, "scaleX", 1.0f,0.0f);
        mAnimatorScaleX.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleX.setRepeatCount(3);
        mAnimatorScaleX.setDuration(1000);

        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(scaleListView, "scaleY", 1.0f,0.0f);
        mAnimatorScaleY.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleY.setRepeatCount(3);
        mAnimatorScaleY.setDuration(1000);

        mAnimatorScaleX.start();
        mAnimatorScaleY.start();
    }

    *//**
     * 位移动画效果
     *//*
    public void translateAniamtor(){
        ListView translateListView = null;
        if(mListViewFront.getVisibility() == View.GONE){
            translateListView = mListViewReverse;
        }else{
            translateListView = mListViewFront;
        }

        ObjectAnimator mAnimatorTranslateX = ObjectAnimator.ofFloat(translateListView, "translationX", 0.0f,screenWidth/2);
        mAnimatorTranslateX.setRepeatMode(Animation.REVERSE);
        mAnimatorTranslateX.setRepeatCount(3);
        mAnimatorTranslateX.setDuration(1000);

        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(translateListView, "translationY", 0.0f,screenHeight/2);
        mAnimatorTranslateY.setRepeatMode(Animation.REVERSE);
        mAnimatorTranslateY.setRepeatCount(3);
        mAnimatorTranslateY.setDuration(1000);

        mAnimatorTranslateX.start();
        mAnimatorTranslateY.start();
    }

    *//**
     * 旋转动画效果
     *//*
    public void rotateAniamtor(){
        ListView rotateListView = null;
        if(mListViewFront.getVisibility() == View.GONE){
            rotateListView = mListViewReverse;
        }else{
            rotateListView = mListViewFront;
        }
        ObjectAnimator mAnimatorRotate = ObjectAnimator.ofFloat(rotateListView, "rotation", 0.0f,360.0f);
        mAnimatorRotate.setRepeatMode(Animation.REVERSE);
        mAnimatorRotate.setRepeatCount(2);
        mAnimatorRotate.setDuration(2000);

        mAnimatorRotate.start();
    }
    *//**
     * 动画集合
     *//*
    public void setAnimator(){
        ListView setListView = null;
        if(mListViewFront.getVisibility() == View.GONE){
            setListView = mListViewReverse;
        }else{
            setListView = mListViewFront;
        }
        setListView.setVisibility(View.GONE);
        if(mImageView.getVisibility() == View.GONE){
            mImageView.setVisibility(View.VISIBLE);
        }
        //代码方式设置动画
        codeAnimatorSet(mImageView);

        //用ViewPropertyAnimator实现动画
        //viewPropertyAnimator(setListView);

        //加载XML文件中的动画
        *//*AnimatorSet mAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.property_animation_animatorset);
        mAnimatorSet.setTarget(mImageView);
        mAnimatorSet.start();*//*
    }

    *//**
     * 使用编码方式实现动画效果
     * @param mImageView
     *//*
    public void codeAnimatorSet(ImageView mImageView){
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorSetRotateX = ObjectAnimator.ofFloat(mImageView, "rotationX", 0.0f,360.0f);
        mAnimatorSetRotateX.setDuration(3000);

        ObjectAnimator mAnimatorSetRotateY = ObjectAnimator.ofFloat(mImageView, "rotationY", 0.0f,360.0f);
        mAnimatorSetRotateY.setDuration(3000);

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f,0.5f);
        mAnimatorScaleX.setRepeatCount(1);
        mAnimatorScaleX.setRepeatMode(REVERSE);
        mAnimatorScaleX.setDuration(1500);

        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f,0.5f);
        mAnimatorScaleY.setRepeatCount(1);
        mAnimatorScaleY.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleY.setDuration(1500);

        mAnimatorSet.play(mAnimatorSetRotateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.play(mAnimatorSetRotateY).before(mAnimatorSetRotateX);

        mAnimatorSet.start();
    }*/

    public void viewPropertyAnimator(ListView mListViewHolder){
        mListViewHolder.animate().cancel();
        mListViewHolder.animate().rotationX(360.0f).setDuration(3000).start();
    }

}
