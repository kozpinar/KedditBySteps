package com.kozpinar.kedditbysteps.core

import android.support.v7.app.AppCompatActivity
import rx.subscriptions.CompositeSubscription

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */
open class RxBaseActivity : AppCompatActivity() {

    var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }
}