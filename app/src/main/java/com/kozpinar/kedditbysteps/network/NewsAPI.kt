package com.kozpinar.kedditbysteps.network

import com.kozpinar.kedditbysteps.models.RedditNewsResponse
import retrofit2.Call

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}