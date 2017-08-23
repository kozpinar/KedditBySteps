package com.kozpinar.kedditbysteps.di.news

import com.kozpinar.kedditbysteps.network.NewsAPI
import com.kozpinar.kedditbysteps.network.NewsRestAPI
import com.kozpinar.kedditbysteps.network.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */
@Module
class NewsModule {
    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditApi): NewsAPI = NewsRestAPI(redditApi)

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi
        = retrofit.create(RedditApi::class.java)
}