package com.kozpinar.kedditbysteps.datas

import rx.Observable

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

class NewsManager() {
    fun getNew(): Observable<List<RedditNewsItem>> {
        return Observable.create {
            subscriber ->
            val news = mutableListOf<RedditNewsItem>()
            (1..10).mapTo(news) {
                RedditNewsItem(
                        "author$it",
                        "Title $it",
                        it, // number of comments
                        1457207701L - it * 200, // time
                        "http://lorempixel.com/200/200/technics/$it", // image url
                        "url"
                )
            }
            subscriber.onNext(news)
        }
    }
}