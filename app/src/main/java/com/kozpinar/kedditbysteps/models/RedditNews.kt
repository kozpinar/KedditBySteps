package com.kozpinar.kedditbysteps.models

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */
class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChlidrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChlidrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)