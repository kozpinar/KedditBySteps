package com.kozpinar.kedditbysteps.features.news.list.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.commons.adapters.ViewType
import com.kozpinar.kedditbysteps.commons.adapters.ViewTypeDelegateAdapter
import com.kozpinar.kedditbysteps.commons.extensions.inflate

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */
class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder (
            parent.inflate(R.layout.news_item_loading)
    )
}