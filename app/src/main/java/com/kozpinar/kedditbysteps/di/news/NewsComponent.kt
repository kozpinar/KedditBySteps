package com.kozpinar.kedditbysteps.di.news

import com.kozpinar.kedditbysteps.di.AppModule
import com.kozpinar.kedditbysteps.di.NetworkModule
import com.kozpinar.kedditbysteps.features.news.list.NewsListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent {
    fun inject(newsListActivity: NewsListActivity)
}