package com.example.rxdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="myApp";

    private TextView textView1;


    private String greeting="Hello From RxJava";
    private Observable<String> myObservable;

    private Observer<String> myObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1=findViewById(R.id.blahblah);



        myObservable=Observable.just(greeting);

        myObserver= new Observer<String>(){

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "on Next invoked");



            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "on Next invoked");
                textView1.setText(s);

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

        myObservable.subscribe(myObserver);
    }
}
