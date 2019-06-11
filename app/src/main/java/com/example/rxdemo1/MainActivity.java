package com.example.rxdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="myApp";

    private TextView textView1;


    private String greeting="Hello From RxJava";
    private Observable<String> myObservable;

    private DisposableObserver<String> myObserver;
    private DisposableObserver<String> myObserver2;

    private CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1=findViewById(R.id.blahblah);

        // use for network interactions
        //myObservable.subscribeOn(Schedulers.io());
        // where user interactions happen
        //myObservable.observeOn(AndroidSchedulers.mainThread()); //

        //myObservable.subscribeOn(Schedulers.io());
        //myObservable.observeOn(AndroidSchedulers.mainThread());




        myObservable=Observable.just(greeting);

        myObserver=new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, "on Next invoked");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "on Next invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "on Next invoked");

            }
        };

        //myObservable.subscribe(myObserver);
        //compositeDisposable.add(myObserver);

        compositeDisposable.add(

        myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myObserver));


        myObserver2=new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, "on Next invoked");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "on Next invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "on Next invoked");

            }
        };

        //myObservable.subscribe(myObserver2);
        //compositeDisposable.add(myObserver2);

        compositeDisposable.add(
                myObservable.subscribeWith(myObserver2)
        );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();

        //disposable.dispose();
    }
}
