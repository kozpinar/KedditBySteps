package com.kozpinar.kedditbysteps.features.news.list.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kozpinar.kedditbysteps.R
import com.kozpinar.kedditbysteps.commons.adapters.ViewType
import com.kozpinar.kedditbysteps.commons.adapters.ViewTypeDelegateAdapter
import com.kozpinar.kedditbysteps.commons.extensions.getFriendlyTime
import com.kozpinar.kedditbysteps.commons.extensions.inflate
import com.kozpinar.kedditbysteps.commons.extensions.loadingImg
import com.kozpinar.kedditbysteps.features.news.list.RedditNewsItem
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

class NewsDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = NewsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class NewsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)
    ) {
        fun bind(item: RedditNewsItem) = with(itemView) {
            thumbnailImageView.loadingImg(item.thumbnail)
            descriptionTextView.text = item.title
            commentsTextView.text = "${item.numComments} comments"
            authorTextView.text = item.author
            timeTextView.text = item.created.getFriendlyTime()
        }
    }
}