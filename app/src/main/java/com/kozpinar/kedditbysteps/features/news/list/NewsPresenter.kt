package com.kozpinar.kedditbysteps.features.news.list

import com.kozpinar.kedditbysteps.core.KedditApp
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by onur on 24.08.2017. KedditBySteps
 */
class NewsPresenter(
        private val newsView: NewsContract.View

) : NewsContract.Presenter {

    private var redditNews: RedditNews? = null


    @Inject lateinit var newsManager: NewsManager

    init {
        KedditApp.newsComponent.inject(this)
        newsView.setPresenter(this)
    }

    override fun loadNews(isRefresh: Boolean): Subscription {

        var str = ""
        if (!(redditNews?.after == null || isRefresh))
            str = redditNews!!.after!!


        val subscription = newsManager.getNews(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            newsView.showNews(redditNews!!.news, forceToClear = isRefresh)
                        },
                        { e ->
                            newsView.showError(e.message)

                        }
                )
        return subscription
    }

    override fun start() {
        loadNews()
    }

    override fun openNewsDetail(news: RedditNewsItem) {

    }

    override fun getRedditNews(): RedditNews? = redditNews

    override fun setRedditNews(news: RedditNews) {
        redditNews = news
    }
}