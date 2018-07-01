package com.me.harris.textviewtest.pattern;

import android.util.Log;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.ResourceSubscriber;

public class SubjectClass {
    CompositeDisposable cd = new CompositeDisposable();
    void testAsync(){
    cd.add(Flowable.range(0,10)
                .subscribeWith(new ResourceSubscriber<Integer>() {
                    Subscription sub;

                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        Log.w("TAG","onsubscribe start");
//                        sub=s;
//                        sub.request(1);
//
//                        Log.w("TAG","onsubscribe end");
//                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.w("TAG","onNext--->"+o);
                        sub.request(1);
                    }
                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                        Log.w("TAG","onComplete");
                    }
                }));


    cd.add(Flowable.range(1,20)
            .subscribeWith(new DisposableSubscriber<Integer>() {
                @Override
                public void onNext(Integer integer) {
                }

                @Override
                public void onError(Throwable t) {
                }

                @Override
                public void onComplete() {
                }
            }));
        final BehaviorSubject<String> sb = BehaviorSubject.createDefault("");
       Disposable d =  sb.subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {

            }
        });
       sb.flatMap(new Function<String, ObservableSource<String>>() {
           @Override
           public ObservableSource<String> apply(String s) throws Exception {
               return Observable.just(s.toUpperCase());
           }
       }).compose(new ObservableTransformer<String, String>() {

           @Override
           public ObservableSource<String> apply(Observable<String> upstream) {
               return upstream.filter(s  -> s.length()>0);
           }
       });








    }
}
