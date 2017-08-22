package com.kozpinar.kedditbysteps.features.news.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.datas.NewsManager
import com.kozpinar.kedditbysteps.features.news.list.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_news_list.*


class NewsListActivity : AppCompatActivity() {

    private val newsManager by lazy { NewsManager() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setup()
    }


    private fun setup() {
        newsListRecyclerView?.setHasFixedSize(true)
        newsListRecyclerView?.layoutManager = LinearLayoutManager(this)
        newsListRecyclerView.adapter = NewsAdapter()

        requestNews()
    }

    private fun requestNews() {

    }
}

