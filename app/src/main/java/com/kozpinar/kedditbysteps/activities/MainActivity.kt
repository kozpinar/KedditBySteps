package com.kozpinar.kedditbysteps.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }


    private fun setup() {
        newsListRecyclerView?.setHasFixedSize(true)
        newsListRecyclerView?.layoutManager = LinearLayoutManager(this)

        newsListRecyclerView.adapter = NewsAdapter()
    }
}

