package com.kozpinar.kedditbysteps.features.news.list

import com.kozpinar.kedditbysteps.network.NewsAPI
import com.kozpinar.kedditbysteps.network.NewsRestAPI
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

@Singleton
class NewsManager @Inject constructor (
        private val apiNews: NewsAPI
) {
    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->
            val callResponse = apiNews.getNews(after, limit)
            val response = callResponse.execute()
            if (response.isSuccessful) {

                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title,
                            item.num_comments, item.created,
                            item.thumbnail, item.url)
                }
                val redditNews = RedditNews(
                        news,
                        dataResponse.after ?: "",
                        dataResponse.before ?: ""
                )
                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }

        }
    }
}