package com.kozpinar.kedditbysteps.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kozpinar.kedditbysteps.commons.adapters.AdapterConstans
import com.kozpinar.kedditbysteps.commons.adapters.ViewType
import com.kozpinar.kedditbysteps.commons.adapters.ViewTypeDelegateAdapter

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */
class NewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object: ViewType {
        override fun getViewType() = AdapterConstans.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstans.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstans.NEWS, NewsDelegateAdapter())
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
}