package com.kozpinar.kedditbysteps.network

import com.kozpinar.kedditbysteps.models.RedditNewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

class NewsRestAPI
@Inject constructor(
        private val redditApi: RedditApi
): NewsAPI {

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse>
        = redditApi.getTop(after, limit)
}