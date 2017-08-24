package com.kozpinar.kedditbysteps.features.news.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.commons.InfiniteScrollListener
import com.kozpinar.kedditbysteps.core.RxBaseActivity
import com.kozpinar.kedditbysteps.features.news.list.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_news_list.*


class NewsListActivity : RxBaseActivity(), NewsContract.View {

    private var presenter: NewsContract.Presenter? = null

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        presenter = NewsPresenter(this)

        setup()
        presenter?.let {
            if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
                it.setRedditNews(savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews)
                (newsListRecyclerView.adapter as NewsAdapter).clearAndAddNews(it.getRedditNews()!!.news)
            } else {
                this.subscriptions.add(it.loadNews())
            }
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (newsListRecyclerView.adapter as NewsAdapter).getNews()
        presenter?.let {
            val redditNews = it.getRedditNews()
            if (redditNews != null && news.size > 0) {
                outState.putParcelable(KEY_REDDIT_NEWS, redditNews.copy(news = news))
            }
        }
    }


    override fun setPresenter(presenter: NewsContract.Presenter) {
        this.presenter = presenter
    }

    override fun showError(msg: String?) {
        Snackbar.make(newsListRecyclerView, msg ?: "", Snackbar.LENGTH_LONG)
                .show()
    }

    override fun showNoNews() {
    }

    override fun showNews(news: List<RedditNewsItem>, forceToClear: Boolean) {
        val newsAdapter = (newsListRecyclerView.adapter as NewsAdapter)
        if (forceToClear) {
            newsAdapter.clearAndAddNews(news)
        } else {
            newsAdapter.addNews(news)
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun swipeToRefresh() {
        presenter!!.loadNews(isRefresh = true)

    }

    private fun setup() {
        newsListRecyclerView.apply {
            setHasFixedSize(true)
            val manager = LinearLayoutManager(baseContext)
            newsListRecyclerView?.layoutManager = manager
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({
                presenter!!.loadNews()
            }, manager))
            adapter = NewsAdapter()
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeToRefresh()
        }
    }
}

