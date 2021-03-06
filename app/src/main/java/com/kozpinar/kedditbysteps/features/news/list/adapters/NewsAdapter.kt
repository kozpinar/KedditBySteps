package com.kozpinar.kedditbysteps.features.news.list.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kozpinar.kedditbysteps.commons.adapters.LoadingDelegateAdapter
import com.kozpinar.kedditbysteps.commons.adapters.AdapterConstants
import com.kozpinar.kedditbysteps.commons.adapters.ViewType
import com.kozpinar.kedditbysteps.commons.adapters.ViewTypeDelegateAdapter
import com.kozpinar.kedditbysteps.features.news.list.RedditNewsItem
import java.util.*

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */
class NewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object: ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun getItemViewType(position: Int): Int = this.items.get(position).getViewType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }


    fun addNews(news: List<RedditNewsItem>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        items.addAll(news)
        items.add(loadingItem)
        notifyDataSetChanged()
    }

    fun getNews(): List<RedditNewsItem> =
            items
                    .filter { it.getViewType() == AdapterConstants.NEWS }
                    .map { it as RedditNewsItem }


    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex}