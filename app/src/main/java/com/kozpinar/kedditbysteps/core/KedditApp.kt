package com.kozpinar.kedditbysteps.core

import android.app.Application
import com.kozpinar.kedditbysteps.di.AppModule
import com.kozpinar.kedditbysteps.di.news.DaggerNewsComponent
import com.kozpinar.kedditbysteps.di.news.NewsComponent

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

class KedditApp: Application() {
    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}