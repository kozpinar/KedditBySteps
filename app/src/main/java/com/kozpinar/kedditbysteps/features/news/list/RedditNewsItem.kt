package com.kozpinar.kedditbysteps.features.news.list

import com.kozpinar.kedditbysteps.commons.adapters.AdapterConstans
import com.kozpinar.kedditbysteps.commons.adapters.ViewType

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */
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