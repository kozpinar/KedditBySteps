package com.kozpinar.kedditbysteps.features.news.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.features.news.list.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_news_list.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription


class NewsListActivity : AppCompatActivity() {

    private val newsManager by lazy { NewsManager() }

    private var subscriptions = CompositeSubscription()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setup()
    }


    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }


    private fun setup() {
        newsListRecyclerView?.setHasFixedSize(true)
        newsListRecyclerView?.layoutManager = LinearLayoutManager(this)
        newsListRecyclerView.adapter = NewsAdapter()

        requestNews()
    }

    private fun requestNews() {
        val subscription = newsManager.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrievedNews ->
                            (newsListRecyclerView.adapter as NewsAdapter).addNews(retrievedNews)
                        },
                        { e ->
                            Snackbar.make(newsListRecyclerView, e.message ?: "", Snackbar.LENGTH_LONG)
                                    .show()
                        }
                )
        subscriptions.add(subscription)
    }
}

