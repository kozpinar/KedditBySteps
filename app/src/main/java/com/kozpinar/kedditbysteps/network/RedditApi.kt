package com.kozpinar.kedditbysteps.network

import com.kozpinar.kedditbysteps.models.RedditNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */
interface RedditApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String)
    : Call<RedditNewsResponse>
}