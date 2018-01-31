package com.me.harris.androidanimations._19_rx_java_2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._09_recyclerView.adapter.Person;
import com.me.harris.androidanimations._09_recyclerView.adapter.PlainAdapter;
import com.me.harris.androidanimations.databinding.ActivityRxJav22Binding;
import com.me.harris.androidanimations.interfaces.OnItemClickListener;
import com.me.harris.androidanimations.utils.LogUtil;
import com.me.harris.androidanimations.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.maybe.MaybeFromCallable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Harris on 2016/12/18.
 */

public class RxJava2MainActivity extends AppCompatActivity implements OnItemClickListener {

    ActivityRxJav22Binding binding;

    PlainAdapter mAdapter;

    List<Person> mList;

    CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_jav_22);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepDatas();
        mAdapter = new PlainAdapter(mList, this);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }


    void prepDatas() {
        mList = new ArrayList<>(20);
        mList.add(new Person("create",R.color.accent_orange));
        mList.add(new Person("Consume",R.color.accent_orange));
        mList.add(new Person("changeMap",R.color.accent_orange));
        mList.add(new Person("simpleApply"));
        mList.add(new Person("testJust"));
        mList.add(new Person("testCompleteable",R.color.md_green_500));
        mList.add(new Person("single"));
        mList.add(new Person("maybe"));
        mList.add(new Person("fromCallable",R.color.md_red_500));
        mList.add(new Person("completable"));
        mList.add(new Person("fromWorker",R.color.md_blue_700));
        mList.add(new Person("Flowable"));
        mList.add(new Person("Observable",R.color.md_green_500));
        mList.add(new Person("Periodical",R.color.md_red_400));
        mList.add(new Person("onComplete",R.color.md_green_500));
    }

    @Override
    public void onItemClicked(View view, int position) {
        if (position != RecyclerView.NO_POSITION) {
            switch (position) {
                case 0:
                    simpleCreate();
                    break;
                case 1:
                    simpleConsume();
                    break;
                case 2:
                    changeMap();
                    break;
                case 3:
                    simpleApply();
                    break;
                case 4:
                    testJust();
                    break;
                case 5:
                    testCompleteable();
                    break;
                case 6:
                    single();
                    break;
                case 7:
                    maybe();
                    break;
                case 8:
                    fromCallable();
                    break;
                case 9:
                    completable();
                    break;
                case 10:
                    fromWorker();
                    break;
                case 11:
                    Flowable();
                    break;
                case 12:
                    Observable();
                case 13:
                    schedulePeriodicallyTasks();
                case 14:
                    testOnComplete();
                default:
                    break;
            }
        }
    }



    void simpleCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                    }
                });
                for (int i = 0; i < 5; i++) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(i);
                        LogUtil.p("subscribe " + i);
                        try {
                            blockThread(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                disposables.add(mDisposable);
            }

            @Override
            public void onNext(Integer value) {
                LogUtil.p("onNext " + value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.p("onError");
            }

            @Override
            public void onComplete() {
                LogUtil.p("onComplete");
            }
        });
    }

    void simpleConsume() {
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
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtil.p("doOnNext " + integer);
            }
        }).doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtil.p("do After Next" + integer);
            }
        }).observeOn(Schedulers.computation()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtil.p("2222222  Accept " + integer);
            }
        }).subscribe(consumer);
    }

    void changeMap() {
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
    }

    void simpleApply() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 5; i++) {
                    e.onNext(i);
                    LogUtil.p("onNext" + i);
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
    }

    void testJust() {
        disposables.add(Observable.just(array).map(new Function<String[], List<String>>() {
            @Override
            public List<String> apply(String[] strings) throws Exception {
                LogUtil.p("apply");
                return Arrays.asList(strings);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<String>>() {
                    @Override
                    public void onNext(List<String> value) {
                        LogUtil.p("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.p("onComplete");
                    }
                }));
    }

    private void fromWorker() {
        Schedulers.computation()
                .createWorker()
                .schedulePeriodically(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.p("do Crazy Stuff on Worker Thread in one Second");
                        Looper lopper = Looper.myLooper();
                        if (lopper == null) {
                            Looper.prepare();
                        }
                        ToastUtils.showTextShort(RxJava2MainActivity.this,"do stuff");
                    }
                }, 1000,500, TimeUnit.MILLISECONDS);

    }

    private void schedulePeriodicallyTasks() {
        AndroidSchedulers.mainThread().schedulePeriodicallyDirect(new TimerTask() {
            @Override
            public void run() {
                LogUtil.p("scheduler work ");
            }
        }, 1000,1000,TimeUnit.MILLISECONDS);
    }

    boolean flag = true;
    void testOnComplete() {
        flag = !flag;

        Maybe observable1, observable2;

        observable1 = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> e) throws Exception {
                binding.linearLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        e.onSuccess("111111111111111111");
                        LogUtil.p("");
                    }
                }, 1500);

            }
        });

        observable2 = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> e) throws Exception {
                binding.linearLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (flag) {
                            e.onSuccess("22222222222222");
                            LogUtil.p("");
                        } else {
                            e.onComplete();
                            LogUtil.p("");
                        }

                    }
                }, 200);

            }
        });

        Maybe.merge(observable1,observable2)
                .subscribeWith(new DisposableSubscriber<String>() {
                    @Override
                    public void onNext(String o) {
                        LogUtil.p("" + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtil.p("" );
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.p(""); // we dont' need call onComplete on the emitter side

                    }
                });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        disposables.dispose();
    }

    void Flowable() {
        Flowable<String> flowable = Flowable.just("Hello");
        Disposable d1 = flowable.subscribeWith(new DisposableSubscriber<String>() {
            @Override
            public void onNext(String s) {
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    void Observable() {
        Observable<String> o = Observable.just("Hello");
        Disposable d2 = o.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    void testCompleteable() {
        Disposable disposable = Observable.just("Hello").subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
//                completable();
    }

    void fromCallable() {
        Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                LogUtil.p("call do on thread any");
                blockThread(2000); // block 2s
                return Arrays.asList(array);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtil.p("");
                    }
                }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                LogUtil.p("");
            }
        }).doOnNext(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                LogUtil.p("" + strings.get(0));
            }
        }).doAfterNext(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                LogUtil.p("" + strings.get(0));
            }
        }).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtil.p("onSubscribe " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> value) {
                LogUtil.p(" get Response " + value.size());
                value.set(0, "change first element!");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                LogUtil.p("");
            }
        });
    }

    void maybe() {
        Maybe<String> maybe = Maybe.just("Hello");
        Disposable d3 = maybe.subscribeWith(new DisposableMaybeObserver<String>() {

            @Override
            public void onSuccess(String value) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
        disposables.add(d3);
    }

    void single() {
        Single<String> s = Single.just("Hello");
        Disposable d4 = s.subscribeOn(Schedulers.io()).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                LogUtil.p("apply");
                return s + " 加工之后";
            }
        }).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DisposableSingleObserver<String>() {

                    @Override
                    public void onSuccess(String value) {
                        LogUtil.p("value is " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        disposables.add(d4);
    }

    void completable() {
        Completable c = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                LogUtil.p("");
                blockThread(2000);
                e.onComplete();
            }
        });
        Disposable d5 = c.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                LogUtil.p("complete on ");
            }

            @Override
            public void onError(Throwable e) {
            }
        });
        disposables.add(d5);
    }

    String[] array = new String[]{"天大圣", "dsa", "dsa", "sdsadsa"};

    void blockThread(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}
