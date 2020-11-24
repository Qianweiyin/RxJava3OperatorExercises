package com.qwy.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.PublishSubject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Observable.just("Hello World")
            .subscribe({ Log.e("QwyJust", "it : " + it) },
                { it.cause },
                { Log.e("QwyJust", "onComplete") })


        /**
         * Convert to lambda
         */
        Observable.just("Hello World")
            .subscribe(object : Consumer<String> {
                override fun accept(t: String?) {
                    Log.e("QwyJust", "t 2 : " + t)
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {
                    t?.printStackTrace()
                }
            }, object : Action {
                override fun run() {
                    Log.e("QwyJust", "onComplete 2 ")
                }
            })

        /**
         *Remove redundant SAM-constructors
         */
        Observable.just("Hello World")
            .subscribe(Consumer<String> { t -> Log.e("QwyJust", "t 2 : " + t) },
                Consumer<Throwable> { t -> t?.printStackTrace() },
                Action { Log.e("QwyJust", "onComplete 2 ") })


        Observable.just("Hello World")
            .subscribe({ t -> Log.e("QwyJust", "t 2 : " + t) },
                { t -> t?.printStackTrace() }, { Log.e("QwyJust", "onComplete 2 ") })


        // ----------------------------    PublishSubject  ------------------------------------ //
        /**
         * PublishSubject 比较容易理解，相对比其它Subject常用
         * 它的Observer只会接收到PublishSubject被订阅之后发送的数据。
         *
         * PublishSubject是最正常的Subject，从哪里订阅就从哪里发射数据。
         */

        val publishSubject: PublishSubject<String> = PublishSubject.create()
        publishSubject.onNext("publishSubject1")
        publishSubject.onNext("publishSubject2")

        publishSubject.subscribe({ string ->
            Log.e("QwyPublishSubject", "string : " + string)
        }, {
            it.cause
        })

        publishSubject.onNext("publishSubject3")
        publishSubject.onNext("publishSubject4")

        // ----------------------------    PublishSubject  ------------------------------------ //





    }
}