package com.me.harris.androidanimations;

import android.support.annotation.NonNull;
import android.util.Log;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    private static final String TAG = "ExampleUnitTest";

    @Test
    public void addition_isaCorrect() throws Exception {
        Observable.just(1)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "map-1:"+Thread.currentThread().getName()); //实际运行在RxNewThreadScheduler-1上
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "map-2:"+Thread.currentThread().getName());//实际运行在RxNewThreadScheduler-1上
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "map-3:"+Thread.currentThread().getName());//实际运行在RxNewThreadScheduler-1上
                        return integer;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "subscribe:"+Thread.currentThread().getName());//实际运行在RxNewThreadScheduler-1上
                    }
                });
    }
}