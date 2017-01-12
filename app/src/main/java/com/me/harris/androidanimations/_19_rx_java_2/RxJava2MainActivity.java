package com.me.harris.androidanimations._19_rx_java_2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityRxjava2Binding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;
import com.me.harris.androidanimations.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Harris on 2016/12/18.
 */

public class RxJava2MainActivity extends BaseAppCompatActivity implements ActionCallBack {

    ActivityRxjava2Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rxjava_2);
        setSupportActionBar(binding.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setCallback(this);
    }

    @Override
    public void onSetStatusBarMode() {
        super.onSetStatusBarMode();
    }

    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 5; i++) {
                            emitter.onNext(i);
                            LogUtil.d("subscribe " + i);
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
                    private Disposable mDisposable;
                    private int i;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtil.e("onNext " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d("onComplete");
                    }
                });
                break;
            case R.id.button2:
                Consumer<Integer> consumer = new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtil.p("accept " + integer);
                    }
                };
                Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        for (int i = 5; i > 0; i--) {
                            LogUtil.p("Subscribe " + i);
                            e.onNext(i);
                        }
                    }
                });
                observable.subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtil.p("doOnNext " + integer);
                    }
                }).observeOn(Schedulers.computation()).doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtil.p("2222222DoOnNext " + integer);
                    }
                }).subscribe(consumer);
                break;
            case R.id.button3:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 5; i++) {
                            emitter.onNext(i);
                            LogUtil.p("onNext");
                        }
                    }
                }).flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            list.add("I am Value " + integer);
                        }
                        return Observable.fromIterable(list).delay(2, TimeUnit.SECONDS);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).
                        subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtil.p("accept " + s);

                    }
                });

                break;
            case R.id.button4 : //调用顺序apply走完>>>onNext走完>>>accept在主线程走完
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        for (int i = 0; i < 5; i++) {
                            e.onNext(i);
                            LogUtil.p("onNext"+i);
                        }
                    }
                }).concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> list = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            LogUtil.p("applyChange integer is " + integer + " i is " + i);
                            list.add("I am value " + i);
                        }
                        return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());//每隔1秒会发送一个List的Observable，传递到accept方法中。先apply进行处理，随后发送到主线程
                    }
                }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtil.p(s);
                    }
                });
                break;
            default:
                break;
        }
    }
}
