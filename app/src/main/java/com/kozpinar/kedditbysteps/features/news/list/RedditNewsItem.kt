package com.kozpinar.kedditbysteps.features.news.list

import com.kozpinar.kedditbysteps.commons.adapters.AdapterConstans
import com.kozpinar.kedditbysteps.commons.adapters.ViewType
import com.kozpinar.kedditbysteps.models.RedditChlidrenResponse

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

data class RedditNews(
        val news: List<RedditNewsItem>,
        val after: String?,
        val before: String?
)

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType {
    override fun getViewType(): Int = AdapterConstans.NEWS
}