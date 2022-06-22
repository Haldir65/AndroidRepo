package com.me.harris.androidanimations.animatingbinding;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityAnimateBindingBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;
import com.me.harris.androidanimations.interfaces.onCheckChangedCallback;

/**
 * https://medium.com/google-developers/android-data-binding-animations-55f6b5956a64#.t71lgcjch
 * Created by Fermi on 2016/12/4.
 */

public class AnimatingBindingActivity extends BaseAppCompatActivity implements ActionCallBack, onCheckChangedCallback {
    private ActivityAnimateBindingBinding binding;
    private GirlsAlpha data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animate_binding);
        setSupportActionBar(binding.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = new GirlsAlpha();
        data.setVisibility(R.id.finalVisibility);
        binding.setData(data);
        binding.setCallback(this);
//       binding.setPresenter(this);

    /* another way of doing things
    binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                TransitionManager.beginDelayedTransition(
                        (ViewGroup)binding.getRoot());
                return super.onPreBind(binding);
            }
        });*/

    }

    @Override
    public void onClickView(View view) {
        //change data , make it reflect view appearance
        data.setVisibility(data.getVisibility() == 0 ? R.id.finalVisibility : 0);
    }

    /** CheckBox的onCheckChanged回调
     * @param compoundButton
     * @param checked
     */
    @Override
    public void completeChanged(CompoundButton compoundButton, boolean checked) {
        data.setVisibility(data.getVisibility() == View.VISIBLE?View.INVISIBLE:View.INVISIBLE);
    }
}
