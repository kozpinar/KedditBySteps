package com.kozpinar.kedditbysteps.features.news.list

import com.kozpinar.kedditbysteps.core.BasePresenter
import com.kozpinar.kedditbysteps.core.BaseView
import rx.Subscription

/**
 * Created by onur on 24.08.2017. KedditBySteps
 */
interface NewsContract {
    interface View: BaseView<Presenter> {
        fun showError(msg: String?)
        fun showNoNews()
        fun showNews(news: List<RedditNewsItem>, forceToClear: Boolean = false)
        fun swipeToRefresh()

    }

    interface Presenter: BasePresenter {
        fun loadNews(isRefresh: Boolean = false): Subscription
        fun getRedditNews(): RedditNews?
        fun setRedditNews(news: RedditNews)
        fun openNewsDetail(news: RedditNewsItem)

    }
}