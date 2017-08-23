package com.kozpinar.kedditbysteps.features.news.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.commons.InfiniteScrollListener
import com.kozpinar.kedditbysteps.commons.RxBaseActivity
import com.kozpinar.kedditbysteps.features.news.list.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_news_list.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class NewsListActivity : RxBaseActivity() {

    private var redditNews: RedditNews? = null

    private val newsManager by lazy { NewsManager() }

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setup()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (newsListRecyclerView.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (newsListRecyclerView.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }


    private fun setup() {

        newsListRecyclerView.apply {
            setHasFixedSize(true)
            val manager = LinearLayoutManager(baseContext)
            newsListRecyclerView?.layoutManager = manager
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({
                requestNews()
            }, manager))
            adapter = NewsAdapter()

        }


    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrievedNews ->
                            redditNews = retrievedNews
                            (newsListRecyclerView.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(newsListRecyclerView, e.message ?: "", Snackbar.LENGTH_LONG)
                                    .show()
                        }
                )
        this.subscriptions.add(subscription)
    }
}

